package com.tencent.mm.sdk.diffdev.a;

import android.os.AsyncTask;
import android.util.Log;
import com.blankj.utilcode.constant.TimeConstants;
import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;

final class f extends AsyncTask<Void, Void, a> {
    private OAuthListener an;
    private String aq;
    private int aw;
    private String url;

    static class a {
        public OAuthErrCode ap;
        public String ax;
        public int ay;

        a() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.tencent.mm.sdk.diffdev.a.f.a e(byte[] r9) {
            /*
            r8 = 1;
            r7 = 0;
            r0 = new com.tencent.mm.sdk.diffdev.a.f$a;
            r0.<init>();
            if (r9 == 0) goto L_0x000c;
        L_0x0009:
            r1 = r9.length;
            if (r1 != 0) goto L_0x0018;
        L_0x000c:
            r1 = "MicroMsg.SDK.NoopingResult";
            r2 = "parse fail, buf is null";
            android.util.Log.e(r1, r2);
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NetworkErr;
            r0.ap = r1;
        L_0x0017:
            return r0;
        L_0x0018:
            r1 = new java.lang.String;	 Catch:{ Exception -> 0x0066 }
            r2 = "utf-8";
            r1.<init>(r9, r2);	 Catch:{ Exception -> 0x0066 }
            r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x004d }
            r2.<init>(r1);	 Catch:{ Exception -> 0x004d }
            r1 = "wx_errcode";
            r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x004d }
            r0.ay = r1;	 Catch:{ Exception -> 0x004d }
            r1 = "MicroMsg.SDK.NoopingResult";
            r3 = "nooping uuidStatusCode = %d";
            r4 = 1;
            r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x004d }
            r5 = 0;
            r6 = r0.ay;	 Catch:{ Exception -> 0x004d }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x004d }
            r4[r5] = r6;	 Catch:{ Exception -> 0x004d }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x004d }
            android.util.Log.d(r1, r3);	 Catch:{ Exception -> 0x004d }
            r1 = r0.ay;	 Catch:{ Exception -> 0x004d }
            switch(r1) {
                case 402: goto L_0x0096;
                case 403: goto L_0x009c;
                case 404: goto L_0x008c;
                case 405: goto L_0x007f;
                case 408: goto L_0x0091;
                case 500: goto L_0x00a2;
                default: goto L_0x0048;
            };	 Catch:{ Exception -> 0x004d }
        L_0x0048:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x004d:
            r1 = move-exception;
            r2 = "MicroMsg.SDK.NoopingResult";
            r3 = "parse json fail, ex = %s";
            r4 = new java.lang.Object[r8];
            r1 = r1.getMessage();
            r4[r7] = r1;
            r1 = java.lang.String.format(r3, r4);
            android.util.Log.e(r2, r1);
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;
            r0.ap = r1;
            goto L_0x0017;
        L_0x0066:
            r1 = move-exception;
            r2 = "MicroMsg.SDK.NoopingResult";
            r3 = "parse fail, build String fail, ex = %s";
            r4 = new java.lang.Object[r8];
            r1 = r1.getMessage();
            r4[r7] = r1;
            r1 = java.lang.String.format(r3, r4);
            android.util.Log.e(r2, r1);
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;
            r0.ap = r1;
            goto L_0x0017;
        L_0x007f:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            r1 = "wx_code";
            r1 = r2.getString(r1);	 Catch:{ Exception -> 0x004d }
            r0.ax = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x008c:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x0091:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_OK;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x0096:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_Timeout;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x009c:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_Cancel;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
        L_0x00a2:
            r1 = com.tencent.mm.sdk.diffdev.OAuthErrCode.WechatAuth_Err_NormalErr;	 Catch:{ Exception -> 0x004d }
            r0.ap = r1;	 Catch:{ Exception -> 0x004d }
            goto L_0x0017;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.sdk.diffdev.a.f.a.e(byte[]):com.tencent.mm.sdk.diffdev.a.f$a");
        }
    }

    public f(String str, OAuthListener oAuthListener) {
        this.aq = str;
        this.an = oAuthListener;
        this.url = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", new Object[]{str});
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (this.aq == null || this.aq.length() == 0) {
            Log.e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
            a aVar = new a();
            aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
            return aVar;
        }
        while (!isCancelled()) {
            String str = this.url + (this.aw == 0 ? "" : "&last=" + this.aw);
            long currentTimeMillis = System.currentTimeMillis();
            byte[] b = e.b(str, TimeConstants.MIN);
            long currentTimeMillis2 = System.currentTimeMillis();
            aVar = a.e(b);
            Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", new Object[]{str, aVar.ap.toString(), Integer.valueOf(aVar.ay), Long.valueOf(currentTimeMillis2 - currentTimeMillis)}));
            if (aVar.ap == OAuthErrCode.WechatAuth_Err_OK) {
                this.aw = aVar.ay;
                if (aVar.ay == g.UUID_SCANED.getCode()) {
                    this.an.onQrcodeScanned();
                } else if (aVar.ay != g.UUID_KEEP_CONNECT.getCode() && aVar.ay == g.UUID_CONFIRM.getCode()) {
                    if (aVar.ax != null && aVar.ax.length() != 0) {
                        return aVar;
                    }
                    Log.e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                    aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    return aVar;
                }
            }
            Log.e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", new Object[]{aVar.ap.toString(), Integer.valueOf(aVar.ay)}));
            return aVar;
        }
        Log.i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
        aVar = new a();
        aVar.ap = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
        return aVar;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a aVar = (a) obj;
        this.an.onAuthFinish(aVar.ap, aVar.ax);
    }
}
