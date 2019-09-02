package cn.jiguang.d.d;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jiguang.api.utils.OutputDataUtil;
import cn.jiguang.d.a.a;
import cn.jiguang.d.b.i;
import cn.jiguang.e.d;
import cn.jpush.android.api.JPushInterface;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    public static i a(String str) {
        try {
            d.c("ConnectingHelper", "sisResponse:" + str);
            i iVar = new i();
            iVar.a(str);
            iVar.b(str);
            if (!iVar.e()) {
                return iVar;
            }
        } catch (Throwable e) {
            d.d("ConnectingHelper", "parseSisInfo crash:", e);
        }
        return null;
    }

    private static void a() {
        d.c("ConnectingHelper", "Action: setRtcToDayTimesWhenRegisterFailed");
        a.k();
    }

    public static void a(Context context, int i, boolean z) {
        if (z) {
            d.j("ConnectingHelper", "Register Failed with server error - code:" + i);
            Object a = cn.jiguang.g.i.a(i);
            if (!TextUtils.isEmpty(a)) {
                d.h("ConnectingHelper", "Local error description: " + a);
            }
        }
        String i2 = cn.jiguang.d.a.d.i(context);
        if (PointerIconCompat.TYPE_CELL == i) {
            cn.jiguang.g.a.a(context, "包名: " + cn.jiguang.d.a.c + " 不存在", -1);
            a();
        } else if (PointerIconCompat.TYPE_CROSSHAIR == i) {
            d.e("ConnectingHelper", "IMEI is duplicated reported by server. Give up now. ");
        } else if (1005 == i) {
            cn.jiguang.g.a.a(context, "包名: " + cn.jiguang.d.a.c + " 与 AppKey:" + i2 + "不匹配", -1);
            a();
        } else if (PointerIconCompat.TYPE_VERTICAL_TEXT == i) {
            cn.jiguang.g.a.a(context, " AppKey:" + i2 + " 非android AppKey", -1);
            a();
        } else if (PointerIconCompat.TYPE_TEXT == i) {
            cn.jiguang.g.a.a(context, " AppKey:" + i2 + " 是无效的AppKey,请确认与JIGUANG web端的AppKey一致", -1);
        } else if (10001 == i) {
            cn.jiguang.g.a.a(context, " 未在manifest中配置AppKey", -1);
        } else if (PointerIconCompat.TYPE_NO_DROP == i) {
            a.c();
        } else {
            d.e("ConnectingHelper", "Unhandled server response error code - " + i);
        }
    }

    public static void a(Context context, long j) {
        d.d("ConnectingHelper", "Action - sendServerTimer");
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("push_login_server_time", j);
            jSONObject.put("push_login_local_time", System.currentTimeMillis());
            bundle.putString("push_to_im_data", jSONObject.toString());
            cn.jiguang.g.a.a(context, "cn.jpush.im.android.action.IM_RESPONSE", bundle);
        } catch (JSONException e) {
            d.g("ConnectingHelper", "jsonException - " + e.getMessage());
        }
    }

    public static void a(Context context, boolean z) {
        d.d("ConnectingHelper", "Action - sendConnectionChanged");
        Bundle bundle = new Bundle();
        bundle.putBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, z);
        cn.jiguang.g.a.a(context, JPushInterface.ACTION_CONNECTION_CHANGE, bundle);
    }

    public static void a(Message message, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("connection", j);
        message.setData(bundle);
        message.sendToTarget();
    }

    public static synchronized byte[] a(String str, int i, byte[] bArr, boolean z, int i2) {
        byte[] toByteArray;
        int i3 = 0;
        synchronized (c.class) {
            if (TextUtils.isEmpty(str) || str.length() != 2 || bArr == null || bArr.length == 0) {
                throw new IllegalArgumentException("flag or body length error");
            }
            OutputDataUtil outputDataUtil = new OutputDataUtil(300);
            outputDataUtil.writeU16(0);
            outputDataUtil.writeByteArray(str.getBytes());
            outputDataUtil.writeU32((long) i);
            outputDataUtil.writeU16(i2);
            outputDataUtil.writeByteArray(bArr);
            outputDataUtil.writeU16At(outputDataUtil.current(), 0);
            if (z) {
                i3 = 1;
            }
            outputDataUtil.writeU8At(i3, 4);
            toByteArray = outputDataUtil.toByteArray();
        }
        return toByteArray;
    }

    public static byte[] a(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        datagramSocket.setSoTimeout(6000);
        datagramSocket.send(datagramPacket);
        DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1024], 1024);
        d.d("ConnectingHelper", "SIS Receiving...");
        datagramSocket.receive(datagramPacket2);
        Object obj = new byte[datagramPacket2.getLength()];
        System.arraycopy(datagramPacket2.getData(), 0, obj, 0, obj.length);
        return obj;
    }

    public static InetAddress b(String str) {
        InetAddress inetAddress = null;
        if (!TextUtils.isEmpty(str)) {
            d.e("ConnectingHelper", "resolved DNS - host:" + str);
            if (Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d{1,2})\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d{1,2})").matcher(str).matches()) {
                try {
                    inetAddress = InetAddress.getByName(str);
                } catch (UnknownHostException e) {
                }
            } else {
                d dVar = new d(str);
                try {
                    dVar.start();
                    dVar.join(3000);
                    inetAddress = dVar.a();
                } catch (InterruptedException e2) {
                    d.i("ConnectingHelper", "DNS checking thread has been interrupted !");
                } catch (Exception e3) {
                    d.i("ConnectingHelper", "DNS checking thread has been Exception error:" + e3);
                }
            }
            d.e("ConnectingHelper", "Resolved DNS - result: " + inetAddress);
        }
        return inetAddress;
    }
}
