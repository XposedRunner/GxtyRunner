package cn.jiguang.a.a.b;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import cn.jiguang.e.d;
import org.json.JSONArray;

final class b extends PhoneStateListener {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        this.a.h = signalStrength.getGsmSignalStrength();
        a.a(this.a, new JSONArray());
        d.g("CellInfoManager", "onSignalStrengthsChanged:" + this.a.h);
        this.a.d();
    }
}
