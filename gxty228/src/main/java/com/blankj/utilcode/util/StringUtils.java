package com.blankj.utilcode.util;

public final class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        if (!(charSequence == null || charSequence2 == null)) {
            int length = charSequence.length();
            if (length == charSequence2.length()) {
                if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
                    return charSequence.equals(charSequence2);
                }
                for (int i = 0; i < length; i++) {
                    if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equalsIgnoreCase(str2);
        }
    }

    public static String null2Length0(String str) {
        return str == null ? "" : str;
    }

    public static int length(CharSequence charSequence) {
        return charSequence == null ? 0 : charSequence.length();
    }

    public static String upperFirstLetter(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return Character.isLowerCase(str.charAt(0)) ? String.valueOf((char) (str.charAt(0) - 32)) + str.substring(1) : str;
    }

    public static String lowerFirstLetter(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return Character.isUpperCase(str.charAt(0)) ? String.valueOf((char) (str.charAt(0) + 32)) + str.substring(1) : str;
    }

    public static String reverse(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        int i = length >> 1;
        char[] toCharArray = str.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            char c = toCharArray[i2];
            toCharArray[i2] = toCharArray[(length - i2) - 1];
            toCharArray[(length - i2) - 1] = c;
        }
        return new String(toCharArray);
    }

    public static String toDBC(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] toCharArray = str.toCharArray();
        int i = 0;
        int length = toCharArray.length;
        while (i < length) {
            if (toCharArray[i] == '　') {
                toCharArray[i] = ' ';
            } else if ('！' > toCharArray[i] || toCharArray[i] > '～') {
                toCharArray[i] = toCharArray[i];
            } else {
                toCharArray[i] = (char) (toCharArray[i] - 65248);
            }
            i++;
        }
        return new String(toCharArray);
    }

    public static String toSBC(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] toCharArray = str.toCharArray();
        int i = 0;
        int length = toCharArray.length;
        while (i < length) {
            if (toCharArray[i] == ' ') {
                toCharArray[i] = '　';
            } else if ('!' > toCharArray[i] || toCharArray[i] > '~') {
                toCharArray[i] = toCharArray[i];
            } else {
                toCharArray[i] = (char) (toCharArray[i] + 65248);
            }
            i++;
        }
        return new String(toCharArray);
    }
}
