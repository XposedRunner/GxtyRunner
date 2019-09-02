package com.blankj.utilcode.util;

import java.util.List;

public final class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static CommandResult execCmd(String str, boolean z) {
        return execCmd(new String[]{str}, z, true);
    }

    public static CommandResult execCmd(List<String> list, boolean z) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult execCmd(String[] strArr, boolean z) {
        return execCmd(strArr, z, true);
    }

    public static CommandResult execCmd(String str, boolean z, boolean z2) {
        return execCmd(new String[]{str}, z, z2);
    }

    public static CommandResult execCmd(List<String> list, boolean z, boolean z2) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.blankj.utilcode.util.ShellUtils.CommandResult execCmd(java.lang.String[] r13, boolean r14, boolean r15) {
        /*
        r1 = 0;
        r2 = -1;
        if (r13 == 0) goto L_0x0007;
    L_0x0004:
        r0 = r13.length;
        if (r0 != 0) goto L_0x0011;
    L_0x0007:
        r0 = new com.blankj.utilcode.util.ShellUtils$CommandResult;
        r1 = "";
        r3 = "";
        r0.<init>(r2, r1, r3);
    L_0x0010:
        return r0;
    L_0x0011:
        r3 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0178, all -> 0x0164 }
        if (r14 == 0) goto L_0x0031;
    L_0x0017:
        r0 = "su";
    L_0x0019:
        r8 = r3.exec(r0);	 Catch:{ Exception -> 0x0178, all -> 0x0164 }
        r3 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x0181, all -> 0x0169 }
        r0 = r8.getOutputStream();	 Catch:{ Exception -> 0x0181, all -> 0x0169 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0181, all -> 0x0169 }
        r4 = r13.length;	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r0 = 0;
    L_0x0028:
        if (r0 >= r4) goto L_0x0071;
    L_0x002a:
        r5 = r13[r0];	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        if (r5 != 0) goto L_0x0034;
    L_0x002e:
        r0 = r0 + 1;
        goto L_0x0028;
    L_0x0031:
        r0 = "sh";
        goto L_0x0019;
    L_0x0034:
        r5 = r5.getBytes();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r3.write(r5);	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r5 = LINE_SEP;	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r3.writeBytes(r5);	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r3.flush();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        goto L_0x002e;
    L_0x0044:
        r0 = move-exception;
        r4 = r3;
        r5 = r1;
        r6 = r1;
        r7 = r8;
        r3 = r0;
        r0 = r1;
    L_0x004b:
        r3.printStackTrace();	 Catch:{ all -> 0x0172 }
        if (r4 == 0) goto L_0x0053;
    L_0x0050:
        r4.close();	 Catch:{ IOException -> 0x0137 }
    L_0x0053:
        if (r6 == 0) goto L_0x0058;
    L_0x0055:
        r6.close();	 Catch:{ IOException -> 0x013d }
    L_0x0058:
        if (r5 == 0) goto L_0x005d;
    L_0x005a:
        r5.close();	 Catch:{ IOException -> 0x0143 }
    L_0x005d:
        if (r7 == 0) goto L_0x0062;
    L_0x005f:
        r7.destroy();
    L_0x0062:
        r3 = new com.blankj.utilcode.util.ShellUtils$CommandResult;
        if (r1 != 0) goto L_0x0158;
    L_0x0066:
        r1 = "";
    L_0x0068:
        if (r0 != 0) goto L_0x015e;
    L_0x006a:
        r0 = "";
    L_0x006c:
        r3.<init>(r2, r1, r0);
        r0 = r3;
        goto L_0x0010;
    L_0x0071:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r0.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r4 = "exit";
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r4 = LINE_SEP;	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r3.writeBytes(r0);	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r3.flush();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r2 = r8.waitFor();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        if (r15 == 0) goto L_0x01ad;
    L_0x0092:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r5.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x016d }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x018a, all -> 0x016d }
        r4.<init>();	 Catch:{ Exception -> 0x018a, all -> 0x016d }
        r7 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0195, all -> 0x016d }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0195, all -> 0x016d }
        r6 = r8.getInputStream();	 Catch:{ Exception -> 0x0195, all -> 0x016d }
        r9 = "UTF-8";
        r0.<init>(r6, r9);	 Catch:{ Exception -> 0x0195, all -> 0x016d }
        r7.<init>(r0);	 Catch:{ Exception -> 0x0195, all -> 0x016d }
        r6 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x01a1, all -> 0x0170 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x01a1, all -> 0x0170 }
        r9 = r8.getErrorStream();	 Catch:{ Exception -> 0x01a1, all -> 0x0170 }
        r10 = "UTF-8";
        r0.<init>(r9, r10);	 Catch:{ Exception -> 0x01a1, all -> 0x0170 }
        r6.<init>(r0);	 Catch:{ Exception -> 0x01a1, all -> 0x0170 }
        r0 = r7.readLine();	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        if (r0 == 0) goto L_0x00e0;
    L_0x00c2:
        r5.append(r0);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
    L_0x00c5:
        r0 = r7.readLine();	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        if (r0 == 0) goto L_0x00e0;
    L_0x00cb:
        r1 = LINE_SEP;	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        r1 = r5.append(r1);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        r1.append(r0);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        goto L_0x00c5;
    L_0x00d5:
        r0 = move-exception;
        r1 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r11 = r3;
        r3 = r0;
        r0 = r4;
        r4 = r11;
        goto L_0x004b;
    L_0x00e0:
        r0 = r6.readLine();	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        if (r0 == 0) goto L_0x0110;
    L_0x00e6:
        r4.append(r0);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
    L_0x00e9:
        r0 = r6.readLine();	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        if (r0 == 0) goto L_0x0110;
    L_0x00ef:
        r1 = LINE_SEP;	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        r1.append(r0);	 Catch:{ Exception -> 0x00d5, all -> 0x00f9 }
        goto L_0x00e9;
    L_0x00f9:
        r0 = move-exception;
        r1 = r6;
    L_0x00fb:
        if (r3 == 0) goto L_0x0100;
    L_0x00fd:
        r3.close();	 Catch:{ IOException -> 0x0149 }
    L_0x0100:
        if (r7 == 0) goto L_0x0105;
    L_0x0102:
        r7.close();	 Catch:{ IOException -> 0x014e }
    L_0x0105:
        if (r1 == 0) goto L_0x010a;
    L_0x0107:
        r1.close();	 Catch:{ IOException -> 0x0153 }
    L_0x010a:
        if (r8 == 0) goto L_0x010f;
    L_0x010c:
        r8.destroy();
    L_0x010f:
        throw r0;
    L_0x0110:
        r0 = r4;
        r1 = r5;
    L_0x0112:
        if (r3 == 0) goto L_0x0117;
    L_0x0114:
        r3.close();	 Catch:{ IOException -> 0x0128 }
    L_0x0117:
        if (r7 == 0) goto L_0x011c;
    L_0x0119:
        r7.close();	 Catch:{ IOException -> 0x012d }
    L_0x011c:
        if (r6 == 0) goto L_0x0121;
    L_0x011e:
        r6.close();	 Catch:{ IOException -> 0x0132 }
    L_0x0121:
        if (r8 == 0) goto L_0x0062;
    L_0x0123:
        r8.destroy();
        goto L_0x0062;
    L_0x0128:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0117;
    L_0x012d:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x011c;
    L_0x0132:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0121;
    L_0x0137:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0053;
    L_0x013d:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0058;
    L_0x0143:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x005d;
    L_0x0149:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0100;
    L_0x014e:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0105;
    L_0x0153:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x010a;
    L_0x0158:
        r1 = r1.toString();
        goto L_0x0068;
    L_0x015e:
        r0 = r0.toString();
        goto L_0x006c;
    L_0x0164:
        r0 = move-exception;
        r3 = r1;
        r7 = r1;
        r8 = r1;
        goto L_0x00fb;
    L_0x0169:
        r0 = move-exception;
        r3 = r1;
        r7 = r1;
        goto L_0x00fb;
    L_0x016d:
        r0 = move-exception;
        r7 = r1;
        goto L_0x00fb;
    L_0x0170:
        r0 = move-exception;
        goto L_0x00fb;
    L_0x0172:
        r0 = move-exception;
        r3 = r4;
        r1 = r5;
        r8 = r7;
        r7 = r6;
        goto L_0x00fb;
    L_0x0178:
        r0 = move-exception;
        r3 = r0;
        r4 = r1;
        r5 = r1;
        r6 = r1;
        r7 = r1;
        r0 = r1;
        goto L_0x004b;
    L_0x0181:
        r0 = move-exception;
        r3 = r0;
        r4 = r1;
        r5 = r1;
        r6 = r1;
        r7 = r8;
        r0 = r1;
        goto L_0x004b;
    L_0x018a:
        r0 = move-exception;
        r4 = r3;
        r6 = r1;
        r7 = r8;
        r3 = r0;
        r0 = r1;
        r11 = r5;
        r5 = r1;
        r1 = r11;
        goto L_0x004b;
    L_0x0195:
        r0 = move-exception;
        r6 = r1;
        r7 = r8;
        r11 = r4;
        r4 = r3;
        r3 = r0;
        r0 = r11;
        r12 = r5;
        r5 = r1;
        r1 = r12;
        goto L_0x004b;
    L_0x01a1:
        r0 = move-exception;
        r6 = r7;
        r7 = r8;
        r11 = r4;
        r4 = r3;
        r3 = r0;
        r0 = r11;
        r12 = r5;
        r5 = r1;
        r1 = r12;
        goto L_0x004b;
    L_0x01ad:
        r0 = r1;
        r6 = r1;
        r7 = r1;
        goto L_0x0112;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ShellUtils.execCmd(java.lang.String[], boolean, boolean):com.blankj.utilcode.util.ShellUtils$CommandResult");
    }
}
