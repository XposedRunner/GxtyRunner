package cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import cn.jpush.android.a;

public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder {
    private int b;
    public int layout;
    public int layoutContentId;
    public int layoutIconDrawable = a.b;
    public int layoutIconId;
    public int layoutTitleId;

    CustomPushNotificationBuilder(Context context) {
        super(context);
    }

    public CustomPushNotificationBuilder(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.layout = i;
        this.layoutIconId = i2;
        this.layoutTitleId = i3;
        this.layoutContentId = i4;
    }

    RemoteViews buildContentView(String str, String str2) {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), this.layout);
        remoteViews.setTextViewText(this.layoutTitleId, str2);
        remoteViews.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
        remoteViews.setTextViewText(this.layoutContentId, str);
        if (this.b != 0) {
            remoteViews.setLong(this.b, "setTime", System.currentTimeMillis());
        }
        return remoteViews;
    }

    public String toString() {
        return "custom_____" + a();
    }

    final String a() {
        return super.a() + "_____" + this.layout + "_____" + this.layoutIconId + "_____" + this.layoutTitleId + "_____" + this.layoutContentId + "_____" + this.layoutIconDrawable + "_____" + this.b;
    }

    final void a(String[] strArr) throws NumberFormatException {
        super.a(strArr);
        this.layout = Integer.parseInt(strArr[5]);
        this.layoutIconId = Integer.parseInt(strArr[6]);
        this.layoutTitleId = Integer.parseInt(strArr[7]);
        this.layoutContentId = Integer.parseInt(strArr[8]);
        this.layoutIconDrawable = Integer.parseInt(strArr[9]);
        if (strArr.length == 11) {
            this.b = Integer.parseInt(strArr[10]);
        }
    }
}
