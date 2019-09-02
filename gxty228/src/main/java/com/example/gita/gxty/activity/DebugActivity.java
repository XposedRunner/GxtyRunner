package com.example.gita.gxty.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.AppUtils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.a;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.i;
import com.example.gita.gxty.utils.k;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.weiget.TitleBar;

public class DebugActivity extends BaseActivity {
    @BindView(2131755232)
    protected TextView debugInfo;
    @BindView(2131755229)
    protected TextView seq1;
    @BindView(2131755230)
    protected TextView seq2;
    @BindView(2131755231)
    protected TextView seq3;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "故障排查");
        this.titleBar.setTitleColor(Color.parseColor("#ffffff"));
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ DebugActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        k.a(c());
        this.seq1.setText("获取跑步参数Seq:" + n.a(c()).b(a.a("run/runPage")));
        this.seq2.setText("上传跑步结果Seq:" + n.a(c()).b(a.a("run/saveRunV2")));
        this.seq3.setText("查看跑步结果Seq:" + n.a(c()).b(a.a("center/runDetailV2")));
        SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(18);
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(19);
        Sensor defaultSensor3 = sensorManager.getDefaultSensor(1);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("用户ID：" + q.a((Context) this).b());
            stringBuffer.append("\n");
            stringBuffer.append("App版本：" + AppUtils.getAppVersionName() + "(build " + AppUtils.getAppVersionCode() + ")");
            stringBuffer.append("\n");
            stringBuffer.append("手机品牌：" + Build.MANUFACTURER + " & " + Build.MODEL);
            stringBuffer.append("\n");
            stringBuffer.append("系统版本：Android " + VERSION.RELEASE + " & " + VERSION.SDK);
            stringBuffer.append("\n");
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                stringBuffer.append("蓝牙状态：不可用");
            } else if (defaultAdapter.isEnabled()) {
                stringBuffer.append("蓝牙状态：已开启");
            } else {
                stringBuffer.append("蓝牙状态：已关闭");
            }
            stringBuffer.append("\n");
            if (b.a(c())) {
                stringBuffer.append("GPS状态：已打开");
            } else {
                stringBuffer.append("GPS状态：已关闭");
            }
            stringBuffer.append("\n");
            if (k.a()) {
                stringBuffer.append("网络状态：已连接");
            } else {
                stringBuffer.append("网络状态：已断开");
            }
            stringBuffer.append("\n");
            if (defaultSensor == null) {
                stringBuffer.append("步行监听器：不可用");
            } else {
                stringBuffer.append("步行监听器：可使用");
            }
            stringBuffer.append("\n");
            if (defaultSensor2 == null) {
                stringBuffer.append("步行计数器：不可用");
            } else {
                stringBuffer.append("步行计数器：可使用");
            }
            stringBuffer.append("\n");
            if (defaultSensor3 == null) {
                stringBuffer.append("加速传感器：不可用");
            } else {
                stringBuffer.append("加速传感器：可使用");
            }
            stringBuffer.append("\n");
            stringBuffer.append("标记：").append(i.a(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.debugInfo.setText(stringBuffer.toString());
        findViewById(R.id.infoBtn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DebugActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    CharSequence charSequence = this.a.seq1.getText().toString() + "\n" + this.a.seq2.getText().toString() + "\n" + this.a.seq3.getText().toString() + "\n" + this.a.debugInfo.getText().toString();
                    if (charSequence != null && !"".equals(charSequence)) {
                        ((ClipboardManager) this.a.c().getSystemService("clipboard")).setText(charSequence);
                        this.a.a("调试信息已复制到剪切板！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected int a() {
        return R.layout.activity_debug;
    }
}
