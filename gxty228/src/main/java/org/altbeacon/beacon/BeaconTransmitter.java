package org.altbeacon.beacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData.Builder;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import org.altbeacon.beacon.logging.LogManager;

@TargetApi(21)
public class BeaconTransmitter {
    public static final int NOT_SUPPORTED_BLE = 2;
    public static final int NOT_SUPPORTED_CANNOT_GET_ADVERTISER = 4;
    public static final int NOT_SUPPORTED_CANNOT_GET_ADVERTISER_MULTIPLE_ADVERTISEMENTS = 5;
    public static final int NOT_SUPPORTED_MIN_SDK = 1;
    @Deprecated
    public static final int NOT_SUPPORTED_MULTIPLE_ADVERTISEMENTS = 3;
    public static final int SUPPORTED = 0;
    private static final String TAG = "BeaconTransmitter";
    private AdvertiseCallback mAdvertiseCallback;
    private int mAdvertiseMode = 0;
    private int mAdvertiseTxPowerLevel = 3;
    private AdvertiseCallback mAdvertisingClientCallback;
    private Beacon mBeacon;
    private BeaconParser mBeaconParser;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private boolean mConnectable = false;
    private boolean mStarted;

    public BeaconTransmitter(Context context, BeaconParser beaconParser) {
        this.mBeaconParser = beaconParser;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
            this.mBluetoothLeAdvertiser = this.mBluetoothAdapter.getBluetoothLeAdvertiser();
            LogManager.d(TAG, "new BeaconTransmitter constructed.  mbluetoothLeAdvertiser is %s", new Object[]{this.mBluetoothLeAdvertiser});
            return;
        }
        LogManager.e(TAG, "Failed to get BluetoothManager", new Object[0]);
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void setBeacon(Beacon beacon) {
        this.mBeacon = beacon;
    }

    public void setBeaconParser(BeaconParser beaconParser) {
        this.mBeaconParser = beaconParser;
    }

    public int getAdvertiseMode() {
        return this.mAdvertiseMode;
    }

    public void setAdvertiseMode(int i) {
        this.mAdvertiseMode = i;
    }

    public int getAdvertiseTxPowerLevel() {
        return this.mAdvertiseTxPowerLevel;
    }

    public void setAdvertiseTxPowerLevel(int i) {
        this.mAdvertiseTxPowerLevel = i;
    }

    public void setConnectable(boolean z) {
        this.mConnectable = z;
    }

    public boolean isConnectable() {
        return this.mConnectable;
    }

    public void startAdvertising(Beacon beacon) {
        startAdvertising(beacon, null);
    }

    public void startAdvertising(Beacon beacon, AdvertiseCallback advertiseCallback) {
        this.mBeacon = beacon;
        this.mAdvertisingClientCallback = advertiseCallback;
        startAdvertising();
    }

    public void startAdvertising() {
        if (this.mBeacon == null) {
            throw new NullPointerException("Beacon cannot be null.  Set beacon before starting advertising");
        }
        int manufacturer = this.mBeacon.getManufacturer();
        int i = -1;
        if (this.mBeaconParser.getServiceUuid() != null) {
            i = this.mBeaconParser.getServiceUuid().intValue();
        }
        if (this.mBeaconParser == null) {
            throw new NullPointerException("You must supply a BeaconParser instance to BeaconTransmitter.");
        }
        byte[] beaconAdvertisementData = this.mBeaconParser.getBeaconAdvertisementData(this.mBeacon);
        String str = "";
        for (int i2 = 0; i2 < beaconAdvertisementData.length; i2++) {
            str = (str + String.format("%02X", new Object[]{Byte.valueOf(beaconAdvertisementData[i2])})) + " ";
        }
        String str2 = TAG;
        String str3 = "Starting advertising with ID1: %s ID2: %s ID3: %s and data: %s of size %s";
        Object[] objArr = new Object[5];
        objArr[0] = this.mBeacon.getId1();
        objArr[1] = this.mBeacon.getIdentifiers().size() > 1 ? this.mBeacon.getId2() : "";
        objArr[2] = this.mBeacon.getIdentifiers().size() > 2 ? this.mBeacon.getId3() : "";
        objArr[3] = str;
        objArr[4] = Integer.valueOf(beaconAdvertisementData.length);
        LogManager.d(str2, str3, objArr);
        try {
            Builder builder = new Builder();
            if (i > 0) {
                ParcelUuid parseUuidFrom = parseUuidFrom(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)});
                builder.addServiceData(parseUuidFrom, beaconAdvertisementData);
                builder.addServiceUuid(parseUuidFrom);
                builder.setIncludeTxPowerLevel(false);
                builder.setIncludeDeviceName(false);
            } else {
                builder.addManufacturerData(manufacturer, beaconAdvertisementData);
            }
            AdvertiseSettings.Builder builder2 = new AdvertiseSettings.Builder();
            builder2.setAdvertiseMode(this.mAdvertiseMode);
            builder2.setTxPowerLevel(this.mAdvertiseTxPowerLevel);
            builder2.setConnectable(this.mConnectable);
            this.mBluetoothLeAdvertiser.startAdvertising(builder2.build(), builder.build(), getAdvertiseCallback());
            LogManager.d(TAG, "Started advertisement with callback: %s", new Object[]{getAdvertiseCallback()});
        } catch (Throwable e) {
            LogManager.e(e, TAG, "Cannot start advertising due to exception", new Object[0]);
        }
    }

    public void stopAdvertising() {
        if (this.mStarted) {
            LogManager.d(TAG, "Stopping advertising with object %s", new Object[]{this.mBluetoothLeAdvertiser});
            this.mAdvertisingClientCallback = null;
            try {
                this.mBluetoothLeAdvertiser.stopAdvertising(getAdvertiseCallback());
            } catch (IllegalStateException e) {
                LogManager.w(TAG, "Bluetooth is turned off. Transmitter stop call failed.", new Object[0]);
            }
            this.mStarted = false;
            return;
        }
        LogManager.d(TAG, "Skipping stop advertising -- not started", new Object[0]);
    }

    public static int checkTransmissionSupported(Context context) {
        if (VERSION.SDK_INT < 21) {
            return 1;
        }
        if (!context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return 2;
        }
        try {
            if (((BluetoothManager) context.getSystemService("bluetooth")).getAdapter().getBluetoothLeAdvertiser() == null) {
                return !((BluetoothManager) context.getSystemService("bluetooth")).getAdapter().isMultipleAdvertisementSupported() ? 5 : 4;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 4;
        }
    }

    private AdvertiseCallback getAdvertiseCallback() {
        if (this.mAdvertiseCallback == null) {
            this.mAdvertiseCallback = new 1(this);
        }
        return this.mAdvertiseCallback;
    }

    private static ParcelUuid parseUuidFrom(byte[] bArr) {
        ParcelUuid fromString = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        } else if (length == 16) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        } else {
            long j;
            if (length == 2) {
                j = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8));
            } else {
                j = ((((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8))) + ((long) ((bArr[2] & 255) << 16))) + ((long) ((bArr[3] & 255) << 24));
            }
            return new ParcelUuid(new UUID(fromString.getUuid().getMostSignificantBits() + (j << 32), fromString.getUuid().getLeastSignificantBits()));
        }
    }
}
