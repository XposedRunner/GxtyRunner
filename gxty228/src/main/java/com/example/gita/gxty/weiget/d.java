package com.example.gita.gxty.weiget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.duudu.abcd.ABCDActivity;
import com.example.gita.gxty.R;
import com.example.gita.gxty.utils.n;
import com.example.gita.gxty.utils.s;

/* compiled from: NoticeDialog */
public class d extends Dialog implements OnClickListener {
    a a;
    private CheckBox b = ((CheckBox) findViewById(R.id.checkBox));
    private TextView c = ((TextView) findViewById(R.id.dOk));
    private TextView d = ((TextView) findViewById(R.id.dCancel));
    private TextView e = ((TextView) findViewById(R.id.dContent));
    private TextView f = ((TextView) findViewById(R.id.dTitle));
    private int g;

    /* compiled from: NoticeDialog */
    public interface a {
        boolean a(d dVar);
    }

    public d(final Context context, boolean z, final int i) {
        super(context, R.style.my_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.notice_dialog);
        this.g = i;
        if (i == 1) {
            this.f.setText(R.string.notice_title);
            this.e.setText(s.a(String.format(context.getString(R.string.notice_content), new Object[]{"<font color='#FF0000'>", "</font>", "<br>"}) + "<br>" + a()));
        } else if (i == 2) {
            this.f.setText(R.string.notice_title2);
            this.e.setText(s.a(String.format(context.getString(R.string.notice_content_2), new Object[]{"<font color='#FF0000'>", "</font>", "<br>"})));
        } else {
            this.f.setText(R.string.notice_title3);
            this.e.setText(s.a(String.format(context.getString(R.string.notice_content_3), new Object[]{"<br>"})));
        }
        if (z) {
            this.b.setVisibility(0);
            this.b.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ d c;

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    n.a(context).a(i, z);
                    if (i == 1) {
                        s.a((CharSequence) "应用设置内可查看\"跑步注意事项\"");
                    }
                }
            });
        } else {
            this.b.setVisibility(8);
        }
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        findViewById(R.id.dpsBtn).setOnClickListener(this);
    }

    private String a() {
        StringBuffer stringBuffer = new StringBuffer();
        if ("xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请做如下设置，否则可能影响跑步结果。</font>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("设置→授权管理→自启动管理→选择【高校体育】→定位功能选择开启此功能");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("设置→电量和性能→[神隐模式→应用设置]/应用配置→【高校体育】→无限制→允许后台");
        } else if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请做如下设置，否则可能影响跑步结果。</font>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("设置→电池→锁屏清理应用/启动管理→选择【高校体育】→取消清理");
        } else if ("oppo".equalsIgnoreCase(Build.MANUFACTURER)) {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请做如下设置，否则可能影响跑步结果。</font>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("设置→电池→其他→选择【高校体育】→关闭后台冻结、异常耗电自动优化、深度睡眠");
            stringBuffer.append("<br>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("手机管家→权限隐私→自启动管理→选择【高校体育】→开启此功能");
        } else if ("vivo".equalsIgnoreCase(Build.MANUFACTURER)) {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请做如下设置，否则可能影响跑步结果。</font>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("i管家→省电管理→后台高耗电→选择【高校体育】→允许继续运行");
            stringBuffer.append("<br>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("i管家→软件管理→自启动管理→选择运【高校体育】→运行自动启动");
        } else if ("SAMSUNG".equalsIgnoreCase(Build.MANUFACTURER)) {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请做如下设置，否则可能影响跑步结果。</font>");
            stringBuffer.append("<br>&#160;&#160;&#160;&#160;");
            stringBuffer.append("设置→应用程序→应用程序管理器菜单下选择【高校体育】，权限→位置信息，开启此功能");
        } else {
            stringBuffer.append("<font color='#FF0000'>5.&#160;由于手机系统的限制，请进入设置关闭电池或性能或自启动针对【高校体育】的优化选项，否则可能影响跑步结果。</font>");
        }
        return stringBuffer.toString();
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (R.id.dOk == view.getId()) {
            if (this.a != null) {
                this.a.a(this);
            }
            dismiss();
        } else if (R.id.dCancel == view.getId()) {
            n.a(getContext()).a(this.g, false);
            dismiss();
        } else if (R.id.dpsBtn == view.getId()) {
            dismiss();
            getContext().startActivity(new Intent(getContext(), ABCDActivity.class));
        }
    }
}
