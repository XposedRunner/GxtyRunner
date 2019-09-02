package cn.jiguang.b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class b extends Binder implements a {
    public b() {
        attachInterface(this, "cn.jiguang.android.IDataShare");
    }

    public static a a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.jiguang.android.IDataShare");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new c(iBinder) : (a) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                boolean a = a();
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 2:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                IBinder a2 = a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2);
                return true;
            case 3:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                a(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("cn.jiguang.android.IDataShare");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
