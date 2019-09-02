package org.altbeacon.beacon;

import android.annotation.TargetApi;
import android.app.Notification;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.logging.Loggers;
import org.altbeacon.beacon.service.BeaconService;
import org.altbeacon.beacon.service.Callback;
import org.altbeacon.beacon.service.MonitoringStatus;
import org.altbeacon.beacon.service.RangeState;
import org.altbeacon.beacon.service.RangedBeacon;
import org.altbeacon.beacon.service.RegionMonitoringState;
import org.altbeacon.beacon.service.RunningAverageRssiFilter;
import org.altbeacon.beacon.service.ScanJobScheduler;
import org.altbeacon.beacon.service.SettingsData;
import org.altbeacon.beacon.service.StartRMData;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.simulator.BeaconSimulator;
import org.altbeacon.beacon.utils.ProcessUtils;

public class BeaconManager {
    public static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 300000;
    public static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 10000;
    public static final long DEFAULT_EXIT_PERIOD = 10000;
    public static final long DEFAULT_FOREGROUND_BETWEEN_SCAN_PERIOD = 0;
    public static final long DEFAULT_FOREGROUND_SCAN_PERIOD = 1100;
    private static final Object SINGLETON_LOCK = new Object();
    @NonNull
    private static final String TAG = "BeaconManager";
    @Nullable
    protected static BeaconSimulator beaconSimulator;
    protected static String distanceModelUpdateUrl = "http://data.altbeacon.org/android-distance.json";
    protected static Class rssiFilterImplClass = RunningAverageRssiFilter.class;
    private static boolean sAndroidLScanningDisabled = false;
    private static long sExitRegionPeriod = 10000;
    @Nullable
    protected static volatile BeaconManager sInstance = null;
    private static boolean sManifestCheckingDisabled = false;
    private long backgroundBetweenScanPeriod = DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD;
    private long backgroundScanPeriod = 10000;
    @NonNull
    private final List<BeaconParser> beaconParsers = new CopyOnWriteArrayList();
    @NonNull
    private final ConcurrentMap<BeaconConsumer, ConsumerInfo> consumers = new ConcurrentHashMap();
    @Nullable
    protected RangeNotifier dataRequestNotifier = null;
    private long foregroundBetweenScanPeriod = 0;
    private long foregroundScanPeriod = DEFAULT_FOREGROUND_SCAN_PERIOD;
    private boolean mBackgroundMode = false;
    private boolean mBackgroundModeUninitialized = true;
    @NonNull
    private final Context mContext;
    @Nullable
    private Notification mForegroundServiceNotification = null;
    private int mForegroundServiceNotificationId = -1;
    private boolean mMainProcess = false;
    @Nullable
    private NonBeaconLeScanCallback mNonBeaconLeScanCallback;
    private boolean mRegionStatePersistenceEnabled = true;
    @Nullable
    private Boolean mScannerInSameProcess = null;
    private boolean mScheduledScanJobsEnabled = false;
    @NonNull
    protected final Set<MonitorNotifier> monitorNotifiers = new CopyOnWriteArraySet();
    @NonNull
    protected final Set<RangeNotifier> rangeNotifiers = new CopyOnWriteArraySet();
    @NonNull
    private final ArrayList<Region> rangedRegions = new ArrayList();
    @Nullable
    private Messenger serviceMessenger = null;

    @Deprecated
    public static void setDebug(boolean z) {
        if (z) {
            LogManager.setLogger(Loggers.verboseLogger());
            LogManager.setVerboseLoggingEnabled(true);
            return;
        }
        LogManager.setLogger(Loggers.empty());
        LogManager.setVerboseLoggingEnabled(false);
    }

    public void setForegroundScanPeriod(long j) {
        this.foregroundScanPeriod = j;
    }

    public void setForegroundBetweenScanPeriod(long j) {
        this.foregroundBetweenScanPeriod = j;
    }

    public void setBackgroundScanPeriod(long j) {
        this.backgroundScanPeriod = j;
    }

    public void setBackgroundBetweenScanPeriod(long j) {
        this.backgroundBetweenScanPeriod = j;
        if (VERSION.SDK_INT >= 26 && this.backgroundBetweenScanPeriod < 900000) {
            LogManager.w(TAG, "Setting a short backgroundBetweenScanPeriod has no effect on Android 8+, which is limited to scanning every ~15 minutes", new Object[0]);
        }
    }

    public static void setRegionExitPeriod(long j) {
        sExitRegionPeriod = j;
        BeaconManager beaconManager = sInstance;
        if (beaconManager != null) {
            beaconManager.applySettings();
        }
    }

    public static long getRegionExitPeriod() {
        return sExitRegionPeriod;
    }

    @NonNull
    public static BeaconManager getInstanceForApplication(@NonNull Context context) {
        BeaconManager beaconManager = sInstance;
        if (beaconManager == null) {
            synchronized (SINGLETON_LOCK) {
                beaconManager = sInstance;
                if (beaconManager == null) {
                    beaconManager = new BeaconManager(context);
                    sInstance = beaconManager;
                }
            }
        }
        return beaconManager;
    }

    protected BeaconManager(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
        checkIfMainProcess();
        if (!sManifestCheckingDisabled) {
            verifyServiceDeclaration();
        }
        this.beaconParsers.add(new AltBeaconParser());
        setScheduledScanJobsEnabledDefault();
    }

    public boolean isMainProcess() {
        return this.mMainProcess;
    }

    public boolean isScannerInDifferentProcess() {
        return (this.mScannerInSameProcess == null || this.mScannerInSameProcess.booleanValue()) ? false : true;
    }

    public void setScannerInSameProcess(boolean z) {
        this.mScannerInSameProcess = Boolean.valueOf(z);
    }

    protected void checkIfMainProcess() {
        ProcessUtils processUtils = new ProcessUtils(this.mContext);
        String processName = processUtils.getProcessName();
        String packageName = processUtils.getPackageName();
        int pid = processUtils.getPid();
        this.mMainProcess = processUtils.isMainProcess();
        LogManager.i(TAG, "BeaconManager started up on pid " + pid + " named '" + processName + "' for application package '" + packageName + "'.  isMainProcess=" + this.mMainProcess, new Object[0]);
    }

    @NonNull
    public List<BeaconParser> getBeaconParsers() {
        return this.beaconParsers;
    }

    @TargetApi(18)
    public boolean checkAvailability() throws BleNotAvailableException {
        if (isBleAvailable()) {
            return ((BluetoothManager) this.mContext.getSystemService("bluetooth")).getAdapter().isEnabled();
        }
        throw new BleNotAvailableException("Bluetooth LE not supported by this device");
    }

    public void bind(@NonNull BeaconConsumer beaconConsumer) {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            synchronized (this.consumers) {
                ConsumerInfo consumerInfo = new ConsumerInfo(this);
                if (((ConsumerInfo) this.consumers.putIfAbsent(beaconConsumer, consumerInfo)) != null) {
                    LogManager.d(TAG, "This consumer is already bound", new Object[0]);
                } else {
                    LogManager.d(TAG, "This consumer is not bound.  Binding now: %s", new Object[]{beaconConsumer});
                    if (this.mScheduledScanJobsEnabled) {
                        LogManager.d(TAG, "Not starting beacon scanning service. Using scheduled jobs", new Object[0]);
                        beaconConsumer.onBeaconServiceConnect();
                    } else {
                        LogManager.d(TAG, "Binding to service", new Object[0]);
                        beaconConsumer.bindService(new Intent(beaconConsumer.getApplicationContext(), BeaconService.class), consumerInfo.beaconServiceConnection, 1);
                    }
                    LogManager.d(TAG, "consumer count is now: %s", new Object[]{Integer.valueOf(this.consumers.size())});
                }
            }
        } else {
            LogManager.w(TAG, "This device does not support bluetooth LE.  Will not start beacon scanning.", new Object[0]);
        }
    }

    public void unbind(@NonNull BeaconConsumer beaconConsumer) {
        if (isBleAvailable()) {
            synchronized (this.consumers) {
                if (this.consumers.containsKey(beaconConsumer)) {
                    LogManager.d(TAG, "Unbinding", new Object[0]);
                    if (this.mScheduledScanJobsEnabled) {
                        LogManager.d(TAG, "Not unbinding from scanning service as we are using scan jobs.", new Object[0]);
                    } else {
                        beaconConsumer.unbindService(((ConsumerInfo) this.consumers.get(beaconConsumer)).beaconServiceConnection);
                    }
                    this.consumers.remove(beaconConsumer);
                    if (this.consumers.size() == 0) {
                        this.serviceMessenger = null;
                        this.mBackgroundMode = false;
                        if (this.mScheduledScanJobsEnabled && VERSION.SDK_INT >= 21) {
                            LogManager.i(TAG, "Cancelling scheduled jobs after unbind of last consumer.", new Object[0]);
                            ScanJobScheduler.getInstance().cancelSchedule(this.mContext);
                        }
                    }
                } else {
                    LogManager.d(TAG, "This consumer is not bound to: %s", new Object[]{beaconConsumer});
                    LogManager.d(TAG, "Bound consumers: ", new Object[0]);
                    for (Entry value : this.consumers.entrySet()) {
                        LogManager.d(TAG, String.valueOf(value.getValue()), new Object[0]);
                    }
                }
            }
            return;
        }
        LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
    }

    public boolean isBound(@NonNull BeaconConsumer beaconConsumer) {
        boolean z;
        synchronized (this.consumers) {
            if (beaconConsumer != null) {
                if (this.consumers.get(beaconConsumer) != null && (this.mScheduledScanJobsEnabled || this.serviceMessenger != null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean isAnyConsumerBound() {
        boolean z;
        synchronized (this.consumers) {
            z = !this.consumers.isEmpty() && (this.mScheduledScanJobsEnabled || this.serviceMessenger != null);
        }
        return z;
    }

    public void setBackgroundMode(boolean z) {
        if (isBleAvailable()) {
            this.mBackgroundModeUninitialized = false;
            if (z != this.mBackgroundMode) {
                this.mBackgroundMode = z;
                try {
                    updateScanPeriods();
                    return;
                } catch (RemoteException e) {
                    LogManager.e(TAG, "Cannot contact service to set scan periods", new Object[0]);
                    return;
                }
            }
            return;
        }
        LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
    }

    public void setEnableScheduledScanJobs(boolean z) {
        if (isAnyConsumerBound()) {
            LogManager.e(TAG, "ScanJob may not be configured because a consumer is already bound.", new Object[0]);
            throw new IllegalStateException("Method must be called before calling bind()");
        } else if (!z || VERSION.SDK_INT >= 21) {
            if (!z && VERSION.SDK_INT >= 26) {
                LogManager.w(TAG, "Disabling ScanJobs on Android 8+ may disable delivery of beacon callbacks in the background unless a foreground service is active.", new Object[0]);
            }
            this.mScheduledScanJobsEnabled = z;
        } else {
            LogManager.e(TAG, "ScanJob may not be configured because JobScheduler is not availble prior to Android 5.0", new Object[0]);
        }
    }

    public boolean getScheduledScanJobsEnabled() {
        return this.mScheduledScanJobsEnabled;
    }

    public boolean getBackgroundMode() {
        return this.mBackgroundMode;
    }

    public long getBackgroundScanPeriod() {
        return this.backgroundScanPeriod;
    }

    public long getBackgroundBetweenScanPeriod() {
        return this.backgroundBetweenScanPeriod;
    }

    public long getForegroundScanPeriod() {
        return this.foregroundScanPeriod;
    }

    public long getForegroundBetweenScanPeriod() {
        return this.foregroundBetweenScanPeriod;
    }

    public boolean isBackgroundModeUninitialized() {
        return this.mBackgroundModeUninitialized;
    }

    @Deprecated
    public void setRangeNotifier(@Nullable RangeNotifier rangeNotifier) {
        this.rangeNotifiers.clear();
        if (rangeNotifier != null) {
            addRangeNotifier(rangeNotifier);
        }
    }

    public void addRangeNotifier(@NonNull RangeNotifier rangeNotifier) {
        if (rangeNotifier != null) {
            this.rangeNotifiers.add(rangeNotifier);
        }
    }

    public boolean removeRangeNotifier(@NonNull RangeNotifier rangeNotifier) {
        return this.rangeNotifiers.remove(rangeNotifier);
    }

    public void removeAllRangeNotifiers() {
        this.rangeNotifiers.clear();
    }

    @Deprecated
    public void setMonitorNotifier(@Nullable MonitorNotifier monitorNotifier) {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            this.monitorNotifiers.clear();
            if (monitorNotifier != null) {
                addMonitorNotifier(monitorNotifier);
            }
        }
    }

    public void addMonitorNotifier(@NonNull MonitorNotifier monitorNotifier) {
        if (!determineIfCalledFromSeparateScannerProcess() && monitorNotifier != null) {
            this.monitorNotifiers.add(monitorNotifier);
        }
    }

    @Deprecated
    public boolean removeMonitoreNotifier(@NonNull MonitorNotifier monitorNotifier) {
        return removeMonitorNotifier(monitorNotifier);
    }

    public boolean removeMonitorNotifier(@NonNull MonitorNotifier monitorNotifier) {
        if (determineIfCalledFromSeparateScannerProcess()) {
            return false;
        }
        return this.monitorNotifiers.remove(monitorNotifier);
    }

    public void removeAllMonitorNotifiers() {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            this.monitorNotifiers.clear();
        }
    }

    @Deprecated
    public void setRegionStatePeristenceEnabled(boolean z) {
        setRegionStatePersistenceEnabled(z);
    }

    public void setRegionStatePersistenceEnabled(boolean z) {
        this.mRegionStatePersistenceEnabled = z;
        if (!isScannerInDifferentProcess()) {
            if (z) {
                MonitoringStatus.getInstanceForApplication(this.mContext).startStatusPreservation();
            } else {
                MonitoringStatus.getInstanceForApplication(this.mContext).stopStatusPreservation();
            }
        }
        applySettings();
    }

    public boolean isRegionStatePersistenceEnabled() {
        return this.mRegionStatePersistenceEnabled;
    }

    public void requestStateForRegion(@NonNull Region region) {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            int i;
            RegionMonitoringState stateOf = MonitoringStatus.getInstanceForApplication(this.mContext).stateOf(region);
            if (stateOf == null || !stateOf.getInside()) {
                i = 0;
            } else {
                i = 1;
            }
            for (MonitorNotifier didDetermineStateForRegion : this.monitorNotifiers) {
                didDetermineStateForRegion.didDetermineStateForRegion(i, region);
            }
        }
    }

    @TargetApi(18)
    public void startRangingBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            synchronized (this.rangedRegions) {
                this.rangedRegions.add(region);
            }
            applyChangesToServices(2, region);
        }
    }

    @TargetApi(18)
    public void stopRangingBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            synchronized (this.rangedRegions) {
                Region region2 = null;
                Iterator it = this.rangedRegions.iterator();
                while (it.hasNext()) {
                    Region region3 = (Region) it.next();
                    if (!region.getUniqueId().equals(region3.getUniqueId())) {
                        region3 = region2;
                    }
                    region2 = region3;
                }
                this.rangedRegions.remove(region2);
            }
            applyChangesToServices(3, region);
        }
    }

    public void applySettings() {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            if (!isAnyConsumerBound()) {
                LogManager.d(TAG, "Not synchronizing settings to service, as it has not started up yet", new Object[0]);
            } else if (isScannerInDifferentProcess()) {
                LogManager.d(TAG, "Synchronizing settings to service", new Object[0]);
                syncSettingsToService();
            } else {
                LogManager.d(TAG, "Not synchronizing settings to service, as it is in the same process", new Object[0]);
            }
        }
    }

    protected void syncSettingsToService() {
        if (this.mScheduledScanJobsEnabled) {
            ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
            return;
        }
        try {
            applyChangesToServices(7, null);
        } catch (RemoteException e) {
            LogManager.e(TAG, "Failed to sync settings to service", new Object[]{e});
        }
    }

    @TargetApi(18)
    public void startMonitoringBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            if (this.mScheduledScanJobsEnabled) {
                MonitoringStatus.getInstanceForApplication(this.mContext).addRegion(region, new Callback(callbackPackageName()));
            }
            applyChangesToServices(4, region);
            if (isScannerInDifferentProcess()) {
                MonitoringStatus.getInstanceForApplication(this.mContext).addLocalRegion(region);
            }
            requestStateForRegion(region);
        }
    }

    @TargetApi(18)
    public void stopMonitoringBeaconsInRegion(@NonNull Region region) throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            if (this.mScheduledScanJobsEnabled) {
                MonitoringStatus.getInstanceForApplication(this.mContext).removeRegion(region);
            }
            applyChangesToServices(5, region);
            if (isScannerInDifferentProcess()) {
                MonitoringStatus.getInstanceForApplication(this.mContext).removeLocalRegion(region);
            }
        }
    }

    @TargetApi(18)
    public void updateScanPeriods() throws RemoteException {
        if (!isBleAvailable()) {
            LogManager.w(TAG, "Method invocation will be ignored.", new Object[0]);
        } else if (!determineIfCalledFromSeparateScannerProcess()) {
            LogManager.d(TAG, "updating background flag to %s", new Object[]{Boolean.valueOf(this.mBackgroundMode)});
            LogManager.d(TAG, "updating scan period to %s, %s", new Object[]{Long.valueOf(getScanPeriod()), Long.valueOf(getBetweenScanPeriod())});
            applyChangesToServices(6, null);
        }
    }

    @TargetApi(18)
    private void applyChangesToServices(int i, Region region) throws RemoteException {
        if (this.mScheduledScanJobsEnabled) {
            if (VERSION.SDK_INT >= 21) {
                ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
            }
        } else if (this.serviceMessenger == null) {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        } else {
            Message obtain = Message.obtain(null, i, 0, 0);
            if (i == 6) {
                obtain.setData(new StartRMData(getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            } else if (i == 7) {
                obtain.setData(new SettingsData().collect(this.mContext).toBundle());
            } else {
                obtain.setData(new StartRMData(region, callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            }
            this.serviceMessenger.send(obtain);
        }
    }

    private String callbackPackageName() {
        LogManager.d(TAG, "callback packageName: %s", new Object[]{this.mContext.getPackageName()});
        return this.mContext.getPackageName();
    }

    @Nullable
    @Deprecated
    public MonitorNotifier getMonitoringNotifier() {
        Iterator it = this.monitorNotifiers.iterator();
        if (it.hasNext()) {
            return (MonitorNotifier) it.next();
        }
        return null;
    }

    @NonNull
    public Set<MonitorNotifier> getMonitoringNotifiers() {
        return Collections.unmodifiableSet(this.monitorNotifiers);
    }

    @Nullable
    @Deprecated
    public RangeNotifier getRangingNotifier() {
        Iterator it = this.rangeNotifiers.iterator();
        if (it.hasNext()) {
            return (RangeNotifier) it.next();
        }
        return null;
    }

    @NonNull
    public Set<RangeNotifier> getRangingNotifiers() {
        return Collections.unmodifiableSet(this.rangeNotifiers);
    }

    @NonNull
    public Collection<Region> getMonitoredRegions() {
        return MonitoringStatus.getInstanceForApplication(this.mContext).regions();
    }

    @NonNull
    public Collection<Region> getRangedRegions() {
        Collection arrayList;
        synchronized (this.rangedRegions) {
            arrayList = new ArrayList(this.rangedRegions);
        }
        return arrayList;
    }

    @Deprecated
    public static void logDebug(String str, String str2) {
        LogManager.d(str, str2, new Object[0]);
    }

    @Deprecated
    public static void logDebug(String str, String str2, Throwable th) {
        LogManager.d(th, str, str2, new Object[0]);
    }

    public static String getDistanceModelUpdateUrl() {
        return distanceModelUpdateUrl;
    }

    public static void setDistanceModelUpdateUrl(@NonNull String str) {
        warnIfScannerNotInSameProcess();
        distanceModelUpdateUrl = str;
    }

    public static void setRssiFilterImplClass(@NonNull Class cls) {
        warnIfScannerNotInSameProcess();
        rssiFilterImplClass = cls;
    }

    public static Class getRssiFilterImplClass() {
        return rssiFilterImplClass;
    }

    public static void setUseTrackingCache(boolean z) {
        RangeState.setUseTrackingCache(z);
        if (sInstance != null) {
            sInstance.applySettings();
        }
    }

    public void setMaxTrackingAge(int i) {
        RangedBeacon.setMaxTrackinAge(i);
    }

    public static void setBeaconSimulator(BeaconSimulator beaconSimulator) {
        warnIfScannerNotInSameProcess();
        beaconSimulator = beaconSimulator;
    }

    @Nullable
    public static BeaconSimulator getBeaconSimulator() {
        return beaconSimulator;
    }

    protected void setDataRequestNotifier(@Nullable RangeNotifier rangeNotifier) {
        this.dataRequestNotifier = rangeNotifier;
    }

    @Nullable
    protected RangeNotifier getDataRequestNotifier() {
        return this.dataRequestNotifier;
    }

    @Nullable
    public NonBeaconLeScanCallback getNonBeaconLeScanCallback() {
        return this.mNonBeaconLeScanCallback;
    }

    public void setNonBeaconLeScanCallback(@Nullable NonBeaconLeScanCallback nonBeaconLeScanCallback) {
        this.mNonBeaconLeScanCallback = nonBeaconLeScanCallback;
    }

    private boolean isBleAvailable() {
        if (VERSION.SDK_INT < 18) {
            LogManager.w(TAG, "Bluetooth LE not supported prior to API 18.", new Object[0]);
            return false;
        } else if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return true;
        } else {
            LogManager.w(TAG, "This device does not support bluetooth LE.", new Object[0]);
            return false;
        }
    }

    private long getScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundScanPeriod;
        }
        return this.foregroundScanPeriod;
    }

    private long getBetweenScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundBetweenScanPeriod;
        }
        return this.foregroundBetweenScanPeriod;
    }

    private void verifyServiceDeclaration() {
        List queryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent(this.mContext, BeaconService.class), 65536);
        if (queryIntentServices != null && queryIntentServices.isEmpty()) {
            throw new ServiceNotDeclaredException(this);
        }
    }

    public static boolean isAndroidLScanningDisabled() {
        return sAndroidLScanningDisabled;
    }

    public static void setAndroidLScanningDisabled(boolean z) {
        sAndroidLScanningDisabled = z;
        BeaconManager beaconManager = sInstance;
        if (beaconManager != null) {
            beaconManager.applySettings();
        }
    }

    @Deprecated
    public static void setsManifestCheckingDisabled(boolean z) {
        sManifestCheckingDisabled = z;
    }

    public static void setManifestCheckingDisabled(boolean z) {
        sManifestCheckingDisabled = z;
    }

    public static boolean getManifestCheckingDisabled() {
        return sManifestCheckingDisabled;
    }

    public void enableForegroundServiceScanning(Notification notification, int i) throws IllegalStateException {
        if (isAnyConsumerBound()) {
            throw new IllegalStateException("May not be called after consumers are already bound.");
        } else if (notification == null) {
            throw new NullPointerException("Notification cannot be null");
        } else {
            setEnableScheduledScanJobs(false);
            this.mForegroundServiceNotification = notification;
            this.mForegroundServiceNotificationId = i;
        }
    }

    public void disableForegroundServiceScanning() throws IllegalStateException {
        if (isAnyConsumerBound()) {
            throw new IllegalStateException("May not be called after consumers are already bound");
        }
        this.mForegroundServiceNotification = null;
        setScheduledScanJobsEnabledDefault();
    }

    public Notification getForegroundServiceNotification() {
        return this.mForegroundServiceNotification;
    }

    public int getForegroundServiceNotificationId() {
        return this.mForegroundServiceNotificationId;
    }

    private boolean determineIfCalledFromSeparateScannerProcess() {
        if (!isScannerInDifferentProcess() || isMainProcess()) {
            return false;
        }
        LogManager.w(TAG, "Ranging/Monitoring may not be controlled from a separate BeaconScanner process.  To remove this warning, please wrap this call in: if (beaconManager.isMainProcess())", new Object[0]);
        return true;
    }

    private static void warnIfScannerNotInSameProcess() {
        BeaconManager beaconManager = sInstance;
        if (beaconManager != null && beaconManager.isScannerInDifferentProcess()) {
            LogManager.w(TAG, "Unsupported configuration change made for BeaconScanner in separate process", new Object[0]);
        }
    }

    private void setScheduledScanJobsEnabledDefault() {
        this.mScheduledScanJobsEnabled = VERSION.SDK_INT >= 26;
    }
}
