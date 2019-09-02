package cn.jiguang.analytics.android.api;

import android.text.TextUtils;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.c.d;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class Account {
    private static final String BIRTHDATE;
    private static final String BUILT_IN_SUFFIX = "$";
    private static final String CREATION_TIME;
    private static final String EMAIL;
    private static final String JSON_KEY_ACCOUNT_ID;
    private static final String JSON_KEY_EXTRA;
    private static final String JSON_KEY_REMOVE;
    private static final String JSON_KEY_SET;
    private static final String NAME;
    private static final String PAID;
    private static final String PHONE;
    private static final String QQ_ID;
    private static final String SEX;
    private static final String TAG;
    private static final String WECHAT_ID;
    private static final String WEIBO_ID;
    private static final Map<String, d> rules = new HashMap();
    private static final String[] z;
    private final String accountId;
    private Map<String, Serializable> extraAttr = new HashMap();
    private Set<String> removeAttr = new HashSet();
    private Map<String, Serializable> setAttr = new HashMap();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "d\f\u0004\u0018JK\u001b";
        r0 = 6;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0096;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x011f;
            case 1: goto L_0x0123;
            case 2: goto L_0x0127;
            case 3: goto L_0x012b;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 63;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0094;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        TAG = r1;
        r1 = "W\n\n\u0018I@0\u0006\u0003KW";
        r0 = 7;
        goto L_0x0008;
    L_0x0031:
        JSON_KEY_REMOVE = r1;
        r1 = "\u0001\n\n\u0016VI";
        r0 = 8;
        goto L_0x0008;
    L_0x0038:
        EMAIL = r1;
        r1 = "D\f\u0004\u0018JK\u001b8\u001e[";
        r0 = 9;
        goto L_0x0008;
    L_0x003f:
        JSON_KEY_ACCOUNT_ID = r1;
        r1 = "\u0001\u0001\u0006\u001aZ";
        r0 = 10;
        goto L_0x0008;
    L_0x0046:
        NAME = r1;
        r1 = "V\n\u0013(^Q\u001b\u0015";
        r0 = 11;
        goto L_0x0008;
    L_0x004d:
        JSON_KEY_SET = r1;
        r1 = "\u0001\u0018\u0002\u0014WD\u001b8\u001e[";
        r0 = 12;
        goto L_0x0008;
    L_0x0054:
        WECHAT_ID = r1;
        r1 = "\u0001\u001e\u0016(VA";
        r0 = 13;
        goto L_0x0008;
    L_0x005b:
        QQ_ID = r1;
        r1 = "\u0001\u001c\u0002\u000f";
        r0 = 14;
        goto L_0x0008;
    L_0x0062:
        SEX = r1;
        r1 = "\u0001\r\u000e\u0005KM\u000b\u0006\u0003Z";
        r0 = 15;
        goto L_0x0008;
    L_0x0069:
        BIRTHDATE = r1;
        r1 = "\u0001\f\u0015\u0012^Q\u0006\b\u0019`Q\u0006\n\u0012";
        r0 = 16;
        goto L_0x0008;
    L_0x0070:
        CREATION_TIME = r1;
        r1 = "\u0001\u0018\u0002\u001e]J0\u000e\u0013";
        r0 = 17;
        goto L_0x0008;
    L_0x0077:
        WEIBO_ID = r1;
        r1 = "@\u0017\u0013\u0005^";
        r0 = 18;
        goto L_0x0008;
    L_0x007e:
        JSON_KEY_EXTRA = r1;
        r1 = "\u0001\u001f\u0006\u001e[";
        r0 = 19;
        goto L_0x0008;
    L_0x0085:
        PAID = r1;
        r1 = "\u0001\u001f\u000f\u0018Q@";
        r0 = 20;
        goto L_0x0008;
    L_0x008d:
        PHONE = r1;
        r1 = "\u0005\u001c\u000f\u0018JI\u000bG\u0015Z\u0005<\u0013\u0005VK\bG\u0018M\u0005!\u0012\u001a]@\u001d";
        r0 = -1;
        goto L_0x0008;
    L_0x0094:
        r5 = r1;
        r1 = r7;
    L_0x0096:
        if (r5 > r6) goto L_0x0011;
    L_0x0098:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x00ad;
            case 1: goto L_0x00b6;
            case 2: goto L_0x00bf;
            case 3: goto L_0x00c8;
            case 4: goto L_0x00d1;
            case 5: goto L_0x00da;
            case 6: goto L_0x002b;
            case 7: goto L_0x0031;
            case 8: goto L_0x0038;
            case 9: goto L_0x003f;
            case 10: goto L_0x0046;
            case 11: goto L_0x004d;
            case 12: goto L_0x0054;
            case 13: goto L_0x005b;
            case 14: goto L_0x0062;
            case 15: goto L_0x0069;
            case 16: goto L_0x0070;
            case 17: goto L_0x0077;
            case 18: goto L_0x007e;
            case 19: goto L_0x0085;
            case 20: goto L_0x008d;
            default: goto L_0x00a4;
        };
    L_0x00a4:
        r3[r2] = r1;
        r2 = 1;
        r1 = "d\f\u0004\u0018JK\u001bG\u0003P\u0005%\u0014\u0018Q\u0005\n\u001f\u0014ZU\u001b\u000e\u0018Q\u001f";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x00ad:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0005\u0013G\u0003W@O\u0011\u0016SP\nG\u0018Y\u0005";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x00b6:
        r3[r2] = r1;
        r2 = 3;
        r1 = "D\f\u0004\u0018JK\u001b8\u001e[\u0005\u001c\u000f\u0018JI\u000bG\u0015Z\u0005\u0001\bWRJ\u001d\u0002WKM\u000e\tW\r\u0010ZG\u0014WD\u001d\u0006\u0014K@\u001d\u0014";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x00bf:
        r3[r2] = r1;
        r2 = 4;
        r1 = "N\n\u001eWLM\u0000\u0012\u001b[\u0005\r\u0002WLQ\u000e\u0015\u0003\u001fR\u0006\u0013\u001f\u001b";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x00c8:
        r3[r2] = r1;
        r2 = 5;
        r1 = "N\n\u001eWVVO\u0002\u001aOQ\u0016KW[JO\t\u0018KM\u0006\t\u0010";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x00d1:
        r3[r2] = r1;
        r2 = 6;
        r1 = "V\n\u00132GQ\u001d\u0006WYD\u0006\u000b\u0012[\u0005UG\u001cZ\\O\u0004\u0016Q\u0005\u0001\b\u0003\u001fV\u001b\u0006\u0005K\u0005\u0018\u000e\u0003W\u0005K";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x00da:
        r3[r2] = r1;
        z = r4;
        r0 = new java.util.HashMap;
        r0.<init>();
        rules = r0;
        r0 = new cn.jiguang.analytics.android.c.c.g;
        r1 = 0;
        r2 = 2;
        r0.<init>(r1, r2);
        r1 = rules;
        r2 = SEX;
        r1.put(r2, r0);
        r1 = rules;
        r2 = PAID;
        r1.put(r2, r0);
        r0 = rules;
        r1 = BIRTHDATE;
        r2 = new cn.jiguang.analytics.android.c.c.e;
        r2.<init>();
        r0.put(r1, r2);
        r0 = rules;
        r1 = PHONE;
        r2 = new cn.jiguang.analytics.android.c.c.h;
        r2.<init>();
        r0.put(r1, r2);
        r0 = rules;
        r1 = EMAIL;
        r2 = new cn.jiguang.analytics.android.c.c.f;
        r2.<init>();
        r0.put(r1, r2);
        return;
    L_0x011f:
        r9 = 37;
        goto L_0x001f;
    L_0x0123:
        r9 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x001f;
    L_0x0127:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x001f;
    L_0x012b:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.Account.<clinit>():void");
    }

    public Account(String str) {
        this.accountId = str;
    }

    private void setBuiltInAttr(String str, Serializable serializable) {
        if (TextUtils.isEmpty(str) || !str.startsWith(BUILT_IN_SUFFIX)) {
            b.h(TAG, z[4]);
        } else if (serializable == null) {
            this.removeAttr.add(str);
            this.setAttr.remove(str);
        } else {
            this.setAttr.put(str, serializable);
            this.removeAttr.remove(str);
        }
    }

    public cn.jiguang.analytics.android.c.c.b fillJson(JSONObject jSONObject) {
        if (this.accountId != null && this.accountId.length() > 255) {
            return new cn.jiguang.analytics.android.c.c.b(1004, z[3]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONObject jSONObject2 = new JSONObject();
            for (Entry entry : this.setAttr.entrySet()) {
                d dVar = (d) rules.get(entry.getKey());
                if (dVar == null || dVar.a((Serializable) entry.getValue())) {
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                } else {
                    stringBuilder.append(z[2]).append((String) entry.getKey()).append(" ").append(dVar.a());
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            for (Entry entry2 : this.extraAttr.entrySet()) {
                Serializable serializable = (Serializable) entry2.getValue();
                if ((serializable instanceof String) || (serializable instanceof Number)) {
                    jSONObject3.put((String) entry2.getKey(), entry2.getValue());
                } else {
                    stringBuilder.append(z[2]).append((String) entry2.getKey()).append(z[0]);
                }
            }
            jSONObject2.put(JSON_KEY_EXTRA, jSONObject3);
            JSONArray jSONArray = new JSONArray();
            for (String put : this.removeAttr) {
                jSONArray.put(put);
            }
            jSONObject.put(JSON_KEY_SET, jSONObject2);
            jSONObject.put(JSON_KEY_REMOVE, jSONArray);
            jSONObject.put(JSON_KEY_ACCOUNT_ID, this.accountId);
        } catch (Exception e) {
            b.f(TAG, new StringBuilder(z[1]).append(e.toString()).toString());
        }
        return TextUtils.isEmpty(stringBuilder.toString()) ? null : new cn.jiguang.analytics.android.c.c.b(1101, stringBuilder.toString());
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setBirthdate(String str) {
        setBuiltInAttr(BIRTHDATE, str);
    }

    public void setCreationTime(Long l) {
        setBuiltInAttr(CREATION_TIME, l);
    }

    public void setEmail(String str) {
        setBuiltInAttr(EMAIL, str);
    }

    public void setExtraAttr(String str, Serializable serializable) {
        if (TextUtils.isEmpty(str)) {
            b.g(TAG, z[5]);
        } else if (str.startsWith(BUILT_IN_SUFFIX)) {
            b.g(TAG, z[6]);
        } else if (serializable == null) {
            this.removeAttr.add(str);
            this.extraAttr.remove(str);
        } else {
            this.extraAttr.put(str, serializable);
            this.removeAttr.remove(str);
        }
    }

    public void setName(String str) {
        setBuiltInAttr(NAME, str);
    }

    public void setPaid(Integer num) {
        setBuiltInAttr(PAID, num);
    }

    public void setPhone(String str) {
        setBuiltInAttr(PHONE, str);
    }

    public void setQqId(String str) {
        setBuiltInAttr(QQ_ID, str);
    }

    public void setSex(Integer num) {
        setBuiltInAttr(SEX, num);
    }

    public void setWechatId(String str) {
        setBuiltInAttr(WECHAT_ID, str);
    }

    public void setWeiboId(String str) {
        setBuiltInAttr(WEIBO_ID, str);
    }
}
