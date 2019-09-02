package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.altbeacon.beacon.client.BeaconDataFactory;
import org.altbeacon.beacon.client.NullBeaconDataFactory;
import org.altbeacon.beacon.distance.DistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;

public class Beacon implements Parcelable, Serializable {
    @Deprecated
    public static final Creator<Beacon> CREATOR = new 1();
    private static final String TAG = "Beacon";
    private static final List<Identifier> UNMODIFIABLE_LIST_OF_IDENTIFIER = Collections.unmodifiableList(new ArrayList());
    private static final List<Long> UNMODIFIABLE_LIST_OF_LONG = Collections.unmodifiableList(new ArrayList());
    protected static BeaconDataFactory beaconDataFactory = new NullBeaconDataFactory();
    protected static DistanceCalculator sDistanceCalculator = null;
    protected static boolean sHardwareEqualityEnforced = false;
    protected int mBeaconTypeCode;
    protected String mBluetoothAddress;
    protected String mBluetoothName;
    protected List<Long> mDataFields;
    protected Double mDistance;
    protected List<Long> mExtraDataFields;
    protected List<Identifier> mIdentifiers;
    protected int mManufacturer;
    protected boolean mMultiFrameBeacon;
    private int mPacketCount;
    protected String mParserIdentifier;
    protected int mRssi;
    private int mRssiMeasurementCount;
    private Double mRunningAverageRssi;
    protected int mServiceUuid;
    protected int mTxPower;

    public static void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        sDistanceCalculator = distanceCalculator;
    }

    public static DistanceCalculator getDistanceCalculator() {
        return sDistanceCalculator;
    }

    public static void setHardwareEqualityEnforced(boolean z) {
        sHardwareEqualityEnforced = z;
    }

    public static boolean getHardwareEqualityEnforced() {
        return sHardwareEqualityEnforced;
    }

    @Deprecated
    protected Beacon(Parcel parcel) {
        int i;
        boolean z = false;
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        int readInt = parcel.readInt();
        this.mIdentifiers = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.mIdentifiers.add(Identifier.parse(parcel.readString()));
        }
        this.mDistance = Double.valueOf(parcel.readDouble());
        this.mRssi = parcel.readInt();
        this.mTxPower = parcel.readInt();
        this.mBluetoothAddress = parcel.readString();
        this.mBeaconTypeCode = parcel.readInt();
        this.mServiceUuid = parcel.readInt();
        readInt = parcel.readInt();
        this.mDataFields = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.mDataFields.add(Long.valueOf(parcel.readLong()));
        }
        readInt = parcel.readInt();
        this.mExtraDataFields = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.mExtraDataFields.add(Long.valueOf(parcel.readLong()));
        }
        this.mManufacturer = parcel.readInt();
        this.mBluetoothName = parcel.readString();
        this.mParserIdentifier = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.mMultiFrameBeacon = z;
        this.mRunningAverageRssi = (Double) parcel.readValue(null);
        this.mRssiMeasurementCount = parcel.readInt();
        this.mPacketCount = parcel.readInt();
    }

    protected Beacon(Beacon beacon) {
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        this.mIdentifiers = new ArrayList(beacon.mIdentifiers);
        this.mDataFields = new ArrayList(beacon.mDataFields);
        this.mExtraDataFields = new ArrayList(beacon.mExtraDataFields);
        this.mDistance = beacon.mDistance;
        this.mRunningAverageRssi = beacon.mRunningAverageRssi;
        this.mPacketCount = beacon.mPacketCount;
        this.mRssiMeasurementCount = beacon.mRssiMeasurementCount;
        this.mRssi = beacon.mRssi;
        this.mTxPower = beacon.mTxPower;
        this.mBluetoothAddress = beacon.mBluetoothAddress;
        this.mBeaconTypeCode = beacon.getBeaconTypeCode();
        this.mServiceUuid = beacon.getServiceUuid();
        this.mBluetoothName = beacon.mBluetoothName;
        this.mParserIdentifier = beacon.mParserIdentifier;
        this.mMultiFrameBeacon = beacon.mMultiFrameBeacon;
        this.mManufacturer = beacon.mManufacturer;
    }

    protected Beacon() {
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        this.mIdentifiers = new ArrayList(1);
        this.mDataFields = new ArrayList(1);
        this.mExtraDataFields = new ArrayList(1);
    }

    public void setRssiMeasurementCount(int i) {
        this.mRssiMeasurementCount = i;
    }

    public int getPacketCount() {
        return this.mPacketCount;
    }

    public void setPacketCount(int i) {
        this.mPacketCount = i;
    }

    public int getMeasurementCount() {
        return this.mRssiMeasurementCount;
    }

    public void setRunningAverageRssi(double d) {
        this.mRunningAverageRssi = Double.valueOf(d);
        this.mDistance = null;
    }

    @Deprecated
    public double getRunningAverageRssi(double d) {
        Double valueOf = Double.valueOf(d);
        this.mRunningAverageRssi = valueOf;
        return valueOf.doubleValue();
    }

    public double getRunningAverageRssi() {
        if (this.mRunningAverageRssi != null) {
            return this.mRunningAverageRssi.doubleValue();
        }
        return (double) this.mRssi;
    }

    public void setRssi(int i) {
        this.mRssi = i;
    }

    public int getManufacturer() {
        return this.mManufacturer;
    }

    public int getServiceUuid() {
        return this.mServiceUuid;
    }

    public Identifier getIdentifier(int i) {
        return (Identifier) this.mIdentifiers.get(i);
    }

    public Identifier getId1() {
        return (Identifier) this.mIdentifiers.get(0);
    }

    public Identifier getId2() {
        return (Identifier) this.mIdentifiers.get(1);
    }

    public Identifier getId3() {
        return (Identifier) this.mIdentifiers.get(2);
    }

    public List<Long> getDataFields() {
        if (this.mDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mDataFields;
        }
        return Collections.unmodifiableList(this.mDataFields);
    }

    public List<Long> getExtraDataFields() {
        if (this.mExtraDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mExtraDataFields;
        }
        return Collections.unmodifiableList(this.mExtraDataFields);
    }

    public void setExtraDataFields(List<Long> list) {
        this.mExtraDataFields = list;
    }

    public List<Identifier> getIdentifiers() {
        if (this.mIdentifiers.getClass().isInstance(UNMODIFIABLE_LIST_OF_IDENTIFIER)) {
            return this.mIdentifiers;
        }
        return Collections.unmodifiableList(this.mIdentifiers);
    }

    public double getDistance() {
        if (this.mDistance == null) {
            double d = (double) this.mRssi;
            if (this.mRunningAverageRssi != null) {
                d = this.mRunningAverageRssi.doubleValue();
            } else {
                LogManager.d(TAG, "Not using running average RSSI because it is null", new Object[0]);
            }
            this.mDistance = calculateDistance(this.mTxPower, d);
        }
        return this.mDistance.doubleValue();
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int getTxPower() {
        return this.mTxPower;
    }

    public int getBeaconTypeCode() {
        return this.mBeaconTypeCode;
    }

    public String getBluetoothAddress() {
        return this.mBluetoothAddress;
    }

    public String getBluetoothName() {
        return this.mBluetoothName;
    }

    public String getParserIdentifier() {
        return this.mParserIdentifier;
    }

    public boolean isMultiFrameBeacon() {
        return this.mMultiFrameBeacon;
    }

    public int hashCode() {
        StringBuilder toStringBuilder = toStringBuilder();
        if (sHardwareEqualityEnforced) {
            toStringBuilder.append(this.mBluetoothAddress);
        }
        return toStringBuilder.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Beacon)) {
            return false;
        }
        Beacon beacon = (Beacon) obj;
        if (this.mIdentifiers.equals(beacon.mIdentifiers)) {
            return sHardwareEqualityEnforced ? getBluetoothAddress().equals(beacon.getBluetoothAddress()) : true;
        } else {
            return false;
        }
    }

    public void requestData(BeaconDataNotifier beaconDataNotifier) {
        beaconDataFactory.requestBeaconData(this, beaconDataNotifier);
    }

    public String toString() {
        return toStringBuilder().toString();
    }

    private StringBuilder toStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Identifier identifier : this.mIdentifiers) {
            if (i > 1) {
                stringBuilder.append(" ");
            }
            stringBuilder.append("id");
            stringBuilder.append(i);
            stringBuilder.append(": ");
            stringBuilder.append(identifier == null ? "null" : identifier.toString());
            i++;
        }
        if (this.mParserIdentifier != null) {
            stringBuilder.append(" type " + this.mParserIdentifier);
        }
        return stringBuilder;
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIdentifiers.size());
        for (Identifier identifier : this.mIdentifiers) {
            parcel.writeString(identifier == null ? null : identifier.toString());
        }
        parcel.writeDouble(getDistance());
        parcel.writeInt(this.mRssi);
        parcel.writeInt(this.mTxPower);
        parcel.writeString(this.mBluetoothAddress);
        parcel.writeInt(this.mBeaconTypeCode);
        parcel.writeInt(this.mServiceUuid);
        parcel.writeInt(this.mDataFields.size());
        for (Long longValue : this.mDataFields) {
            parcel.writeLong(longValue.longValue());
        }
        parcel.writeInt(this.mExtraDataFields.size());
        for (Long longValue2 : this.mExtraDataFields) {
            parcel.writeLong(longValue2.longValue());
        }
        parcel.writeInt(this.mManufacturer);
        parcel.writeString(this.mBluetoothName);
        parcel.writeString(this.mParserIdentifier);
        parcel.writeByte((byte) (this.mMultiFrameBeacon ? 1 : 0));
        parcel.writeValue(this.mRunningAverageRssi);
        parcel.writeInt(this.mRssiMeasurementCount);
        parcel.writeInt(this.mPacketCount);
    }

    public boolean isExtraBeaconData() {
        return this.mIdentifiers.size() == 0 && this.mDataFields.size() != 0;
    }

    protected static Double calculateDistance(int i, double d) {
        if (getDistanceCalculator() != null) {
            return Double.valueOf(getDistanceCalculator().calculateDistance(i, d));
        }
        LogManager.e(TAG, "Distance calculator not set.  Distance will bet set to -1", new Object[0]);
        return Double.valueOf(-1.0d);
    }
}
