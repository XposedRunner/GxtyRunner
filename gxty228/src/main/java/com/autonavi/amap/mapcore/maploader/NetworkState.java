package com.autonavi.amap.mapcore.maploader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;

public class NetworkState {
    private boolean isNetReceiverRegistered = false;
    private NetworkChangeListener mNetworkListener;
    private MyBroadcastReceiver netChangeReceiver = null;

    public interface NetworkChangeListener {
        void networkStateChanged(Context context);
    }

    static class MyBroadcastReceiver extends BroadcastReceiver {
        WeakReference<NetworkChangeListener> reference = null;

        public MyBroadcastReceiver(NetworkChangeListener networkChangeListener) {
            this.reference = new WeakReference(networkChangeListener);
        }

        public void onReceive(Context context, Intent intent) {
            if (this.reference != null && this.reference.get() != null) {
                ((NetworkChangeListener) this.reference.get()).networkStateChanged(context);
            }
        }
    }

    public void setNetworkListener(NetworkChangeListener networkChangeListener) {
        this.mNetworkListener = networkChangeListener;
    }

    public void registerNetChangeReceiver(Context context, boolean z) {
        if (z) {
            if (!this.isNetReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                if (this.netChangeReceiver == null) {
                    this.netChangeReceiver = new MyBroadcastReceiver(this.mNetworkListener);
                }
                context.registerReceiver(this.netChangeReceiver, intentFilter);
            }
        } else if (this.isNetReceiverRegistered && this.netChangeReceiver != null) {
            context.unregisterReceiver(this.netChangeReceiver);
            this.netChangeReceiver = null;
        }
        this.isNetReceiverRegistered = z;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return activeNetworkInfo;
            }
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo == null) {
                return null;
            }
            NetworkInfo networkInfo;
            int i = 0;
            while (i < allNetworkInfo.length) {
                if (allNetworkInfo[i] != null && allNetworkInfo[i].isConnected()) {
                    networkInfo = allNetworkInfo[i];
                    break;
                }
                i++;
            }
            networkInfo = activeNetworkInfo;
            return networkInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
