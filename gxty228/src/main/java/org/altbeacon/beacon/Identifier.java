package org.altbeacon.beacon;

import android.annotation.TargetApi;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

public class Identifier implements Serializable, Comparable<Identifier> {
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("^0|[1-9][0-9]*$");
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern HEX_PATTERN = Pattern.compile("^0x[0-9A-Fa-f]*$");
    private static final Pattern HEX_PATTERN_NO_PREFIX = Pattern.compile("^[0-9A-Fa-f]*$");
    private static final int MAX_INTEGER = 65535;
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9A-Fa-f]{8}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{12}$");
    private final byte[] mValue;

    public static Identifier parse(String str) {
        return parse(str, -1);
    }

    public static Identifier parse(String str, int i) {
        if (str == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"stringValue\" is null.");
        } else if (HEX_PATTERN.matcher(str).matches()) {
            return parseHex(str.substring(2), i);
        } else {
            if (UUID_PATTERN.matcher(str).matches()) {
                return parseHex(str.replace("-", ""), i);
            }
            if (DECIMAL_PATTERN.matcher(str).matches()) {
                try {
                    int intValue = Integer.valueOf(str).intValue();
                    if (i <= 0 || i == 2) {
                        return fromInt(intValue);
                    }
                    return fromLong((long) intValue, i);
                } catch (Throwable th) {
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Unable to parse Identifier in decimal format.", th);
                }
            } else if (HEX_PATTERN_NO_PREFIX.matcher(str).matches()) {
                return parseHex(str, i);
            } else {
                throw new IllegalArgumentException("Unable to parse Identifier.");
            }
        }
    }

    private static Identifier parseHex(String str, int i) {
        int length;
        String str2 = (str.length() % 2 == 0 ? "" : "0") + str.toUpperCase();
        if (i > 0 && i < str2.length() / 2) {
            str2 = str2.substring(str2.length() - (i * 2));
        }
        if (i > 0 && i > str2.length() / 2) {
            length = (i * 2) - str2.length();
            StringBuilder stringBuilder = new StringBuilder();
            while (stringBuilder.length() < length) {
                stringBuilder.append("0");
            }
            str2 = stringBuilder.toString() + str2;
        }
        byte[] bArr = new byte[(str2.length() / 2)];
        for (length = 0; length < bArr.length; length++) {
            bArr[length] = (byte) (Integer.parseInt(str2.substring(length * 2, (length * 2) + 2), 16) & 255);
        }
        return new Identifier(bArr);
    }

    public static Identifier fromLong(long j, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Identifier length must be > 0.");
        }
        byte[] bArr = new byte[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return new Identifier(bArr);
    }

    public static Identifier fromInt(int i) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("Identifiers can only be constructed from integers between 0 and 65535 (inclusive).");
        }
        return new Identifier(new byte[]{(byte) (i >> 8), (byte) i});
    }

    @TargetApi(9)
    public static Identifier fromBytes(byte[] bArr, int i, int i2, boolean z) {
        if (bArr == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"bytes\" is null.");
        } else if (i < 0 || i > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("start < 0 || start > bytes.length");
        } else if (i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("end > bytes.length");
        } else if (i > i2) {
            throw new IllegalArgumentException("start > end");
        } else {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
            if (z) {
                reverseArray(copyOfRange);
            }
            return new Identifier(copyOfRange);
        }
    }

    public static Identifier fromUuid(UUID uuid) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        return new Identifier(allocate.array());
    }

    @Deprecated
    public Identifier(Identifier identifier) {
        if (identifier == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"identifier\" is null.");
        }
        this.mValue = identifier.mValue;
    }

    protected Identifier(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"value\" is null.");
        }
        this.mValue = bArr;
    }

    public String toString() {
        if (this.mValue.length == 2) {
            return Integer.toString(toInt());
        }
        if (this.mValue.length == 16) {
            return toUuid().toString();
        }
        return toHexString();
    }

    public int toInt() {
        int i = 0;
        if (this.mValue.length > 2) {
            throw new UnsupportedOperationException("Only supported for Identifiers with max byte length of 2");
        }
        int i2 = 0;
        while (i < this.mValue.length) {
            i2 |= (this.mValue[i] & 255) << (((this.mValue.length - i) - 1) * 8);
            i++;
        }
        return i2;
    }

    @TargetApi(9)
    public byte[] toByteArrayOfSpecifiedEndianness(boolean z) {
        byte[] copyOf = Arrays.copyOf(this.mValue, this.mValue.length);
        if (!z) {
            reverseArray(copyOf);
        }
        return copyOf;
    }

    private static void reverseArray(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int length = (bArr.length - i) - 1;
            byte b = bArr[i];
            bArr[i] = bArr[length];
            bArr[length] = b;
        }
    }

    public int getByteCount() {
        return this.mValue.length;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mValue);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Identifier)) {
            return false;
        }
        return Arrays.equals(this.mValue, ((Identifier) obj).mValue);
    }

    public String toHexString() {
        int i = 0;
        int length = this.mValue.length;
        char[] cArr = new char[((length * 2) + 2)];
        cArr[0] = '0';
        cArr[1] = 'x';
        int i2 = 2;
        while (i < length) {
            int i3 = i2 + 1;
            cArr[i2] = HEX_DIGITS[(this.mValue[i] & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) >>> 4];
            i2 = i3 + 1;
            cArr[i3] = HEX_DIGITS[this.mValue[i] & 15];
            i++;
        }
        return new String(cArr);
    }

    @Deprecated
    public String toUuidString() {
        return toUuid().toString();
    }

    public UUID toUuid() {
        if (this.mValue.length != 16) {
            throw new UnsupportedOperationException("Only Identifiers backed by a byte array with length of exactly 16 can be UUIDs.");
        }
        LongBuffer asLongBuffer = ByteBuffer.wrap(this.mValue).asLongBuffer();
        return new UUID(asLongBuffer.get(), asLongBuffer.get());
    }

    public byte[] toByteArray() {
        return (byte[]) this.mValue.clone();
    }

    public int compareTo(Identifier identifier) {
        if (this.mValue.length == identifier.mValue.length) {
            int i = 0;
            while (i < this.mValue.length) {
                if (this.mValue[i] == identifier.mValue[i]) {
                    i++;
                } else if (this.mValue[i] >= identifier.mValue[i]) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        } else if (this.mValue.length < identifier.mValue.length) {
            return -1;
        } else {
            return 1;
        }
    }
}
