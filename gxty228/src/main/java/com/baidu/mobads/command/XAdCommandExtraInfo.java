package com.baidu.mobads.command;

import android.os.Parcel;
import android.os.Parcelable;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.vo.XAdInstanceInfo;
import com.sina.weibo.sdk.exception.WeiboAuthException;

public abstract class XAdCommandExtraInfo implements Parcelable {
    public String adid;
    public String appsec;
    public String appsid;
    public Boolean autoOpen;
    public Boolean canCancel;
    public String canDelete;
    public String iconfrom;
    protected XAdInstanceInfo mAdInstanceInfo;
    public int mIntTesting4LM;
    public String mProdType;
    public String mStringTesting4LM;
    public String packageName;
    public String packageNameOfPubliser;
    public Boolean popNotif;
    public String qk;
    public String sn;
    public String url;
    public String v;

    public XAdCommandExtraInfo(String str, IXAdInstanceInfo iXAdInstanceInfo) {
        this.appsec = "";
        this.appsid = "";
        this.packageNameOfPubliser = "";
        this.adid = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        this.packageName = "";
        this.qk = "";
        this.autoOpen = Boolean.valueOf(true);
        this.v = "";
        this.sn = "";
        this.iconfrom = "";
        this.canCancel = Boolean.valueOf(true);
        this.canDelete = "";
        this.popNotif = Boolean.valueOf(true);
        this.mIntTesting4LM = GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER;
        this.mStringTesting4LM = "this is the test string";
        this.mProdType = str;
        this.mAdInstanceInfo = (XAdInstanceInfo) iXAdInstanceInfo;
    }

    protected XAdCommandExtraInfo(Parcel parcel) {
        this.appsec = "";
        this.appsid = "";
        this.packageNameOfPubliser = "";
        this.adid = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        this.packageName = "";
        this.qk = "";
        this.autoOpen = Boolean.valueOf(true);
        this.v = "";
        this.sn = "";
        this.iconfrom = "";
        this.canCancel = Boolean.valueOf(true);
        this.canDelete = "";
        this.popNotif = Boolean.valueOf(true);
        this.mAdInstanceInfo = (XAdInstanceInfo) parcel.readParcelable(XAdInstanceInfo.class.getClassLoader());
        this.mProdType = parcel.readString();
        this.mIntTesting4LM = parcel.readInt();
        this.mStringTesting4LM = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAdInstanceInfo, i);
        parcel.writeString(this.mProdType);
        parcel.writeInt(this.mIntTesting4LM);
        parcel.writeString(this.mStringTesting4LM);
    }

    public IXAdInstanceInfo getAdInstanceInfo() {
        return this.mAdInstanceInfo;
    }

    public Boolean isValid() {
        return Boolean.valueOf(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equalsIgnoreCase(this.adid));
    }
}
