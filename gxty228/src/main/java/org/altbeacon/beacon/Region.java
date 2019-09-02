package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Region implements Parcelable, Serializable {
    public static final Creator<Region> CREATOR = new 1();
    private static final Pattern MAC_PATTERN = Pattern.compile("^[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}$");
    private static final String TAG = "Region";
    protected final String mBluetoothAddress;
    protected final List<Identifier> mIdentifiers;
    protected final String mUniqueId;

    public Region(String str, Identifier identifier, Identifier identifier2, Identifier identifier3) {
        this.mIdentifiers = new ArrayList(3);
        this.mIdentifiers.add(identifier);
        this.mIdentifiers.add(identifier2);
        this.mIdentifiers.add(identifier3);
        this.mUniqueId = str;
        this.mBluetoothAddress = null;
        if (str == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Region(String str, List<Identifier> list) {
        this(str, list, null);
    }

    public Region(String str, List<Identifier> list, String str2) {
        validateMac(str2);
        this.mIdentifiers = new ArrayList(list);
        this.mUniqueId = str;
        this.mBluetoothAddress = str2;
        if (str == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Region(String str, String str2) {
        validateMac(str2);
        this.mBluetoothAddress = str2;
        this.mUniqueId = str;
        this.mIdentifiers = new ArrayList();
        if (str == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Identifier getId1() {
        return getIdentifier(0);
    }

    public Identifier getId2() {
        return getIdentifier(1);
    }

    public Identifier getId3() {
        return getIdentifier(2);
    }

    public Identifier getIdentifier(int i) {
        return this.mIdentifiers.size() > i ? (Identifier) this.mIdentifiers.get(i) : null;
    }

    public String getUniqueId() {
        return this.mUniqueId;
    }

    public String getBluetoothAddress() {
        return this.mBluetoothAddress;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean matchesBeacon(org.altbeacon.beacon.Beacon r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.mIdentifiers;
        r0 = r0.size();
    L_0x0007:
        r3 = r0 + -1;
        if (r3 < 0) goto L_0x0032;
    L_0x000b:
        r0 = r5.mIdentifiers;
        r0 = r0.get(r3);
        r0 = (org.altbeacon.beacon.Identifier) r0;
        r2 = 0;
        r4 = r6.mIdentifiers;
        r4 = r4.size();
        if (r3 >= r4) goto L_0x0020;
    L_0x001c:
        r2 = r6.getIdentifier(r3);
    L_0x0020:
        if (r2 != 0) goto L_0x0024;
    L_0x0022:
        if (r0 != 0) goto L_0x002e;
    L_0x0024:
        if (r2 == 0) goto L_0x0030;
    L_0x0026:
        if (r0 == 0) goto L_0x0030;
    L_0x0028:
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x0030;
    L_0x002e:
        r0 = r1;
    L_0x002f:
        return r0;
    L_0x0030:
        r0 = r3;
        goto L_0x0007;
    L_0x0032:
        r0 = r5.mBluetoothAddress;
        if (r0 == 0) goto L_0x0042;
    L_0x0036:
        r0 = r5.mBluetoothAddress;
        r2 = r6.mBluetoothAddress;
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 != 0) goto L_0x0042;
    L_0x0040:
        r0 = r1;
        goto L_0x002f;
    L_0x0042:
        r0 = 1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.Region.matchesBeacon(org.altbeacon.beacon.Beacon):boolean");
    }

    public int hashCode() {
        return this.mUniqueId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Region) {
            return ((Region) obj).mUniqueId.equals(this.mUniqueId);
        }
        return false;
    }

    public boolean hasSameIdentifiers(Region region) {
        if (region.mIdentifiers.size() != this.mIdentifiers.size()) {
            return false;
        }
        int i = 0;
        while (i < region.mIdentifiers.size()) {
            if (region.getIdentifier(i) == null && getIdentifier(i) != null) {
                return false;
            }
            if (region.getIdentifier(i) != null && getIdentifier(i) == null) {
                return false;
            }
            if ((region.getIdentifier(i) != null || getIdentifier(i) != null) && !region.getIdentifier(i).equals(getIdentifier(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public String toString() {
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
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUniqueId);
        parcel.writeString(this.mBluetoothAddress);
        parcel.writeInt(this.mIdentifiers.size());
        for (Identifier identifier : this.mIdentifiers) {
            if (identifier != null) {
                parcel.writeString(identifier.toString());
            } else {
                parcel.writeString(null);
            }
        }
    }

    protected Region(Parcel parcel) {
        this.mUniqueId = parcel.readString();
        this.mBluetoothAddress = parcel.readString();
        int readInt = parcel.readInt();
        this.mIdentifiers = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            String readString = parcel.readString();
            if (readString == null) {
                this.mIdentifiers.add(null);
            } else {
                this.mIdentifiers.add(Identifier.parse(readString));
            }
        }
    }

    private void validateMac(String str) throws IllegalArgumentException {
        if (str != null && !MAC_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException("Invalid mac address: '" + str + "' Must be 6 hex bytes separated by colons.");
        }
    }

    @Deprecated
    public Region clone() {
        return new Region(this.mUniqueId, this.mIdentifiers, this.mBluetoothAddress);
    }
}
