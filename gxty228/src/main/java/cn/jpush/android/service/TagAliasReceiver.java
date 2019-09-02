package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.d.e;

public class TagAliasReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            e.h("TagAliasReceiver", "TagAliasOperator onReceive intent is null");
            return;
        }
        long longExtra = intent.getLongExtra("tagalias_seqid", -1);
        int intExtra = intent.getIntExtra("tagalias_errorcode", 0);
        if (longExtra == -1) {
            e.g("TagAliasReceiver", "TagAliasOperator onReceive rid is invalide");
        } else {
            d.a().a(context.getApplicationContext(), longExtra, intExtra, intent);
        }
    }
}
