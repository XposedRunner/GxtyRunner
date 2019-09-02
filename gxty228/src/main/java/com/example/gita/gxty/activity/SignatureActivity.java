package com.example.gita.gxty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataSignature;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.utils.b;
import com.example.gita.gxty.utils.q;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.TitleBar;
import com.lzy.okgo.a;
import com.lzy.okgo.request.GetRequest;

public class SignatureActivity extends BaseActivity {
    private int f = 30;
    private String g;
    @BindView(2131755280)
    protected EditText signature;
    @BindView(2131755435)
    protected TextView tips;
    @BindView(2131755192)
    protected TitleBar titleBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.titleBar.setLeftImageResource(R.mipmap.back);
        this.titleBar.setTitle((CharSequence) "个性签名");
        this.titleBar.setTitleColor(-1);
        this.titleBar.setLeftClickListener(new OnClickListener(this) {
            final /* synthetic */ SignatureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.g = (String) q.a(c()).b("signature", "");
        this.signature.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SignatureActivity a;
            private CharSequence b;
            private int c;
            private int d;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.b = charSequence;
            }

            public void afterTextChanged(Editable editable) {
                this.a.tips.setText("" + (this.a.f - editable.length()) + "/30");
                this.c = this.a.signature.getSelectionStart();
                this.d = this.a.signature.getSelectionEnd();
                if (this.b.length() > this.a.f) {
                    editable.delete(this.c - 1, this.d);
                    int i = this.d;
                    this.a.signature.setText(editable);
                    this.a.signature.setSelection(i);
                }
            }
        });
        this.signature.setText(this.g);
        this.signature.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SignatureActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    Object dataSignature = new DataSignature();
                    dataSignature.signature = this.a.signature.getText().toString();
                    dataSignature.userid = q.a(this.a.c()).b();
                    DataBean a = b.a(dataSignature);
                    ((GetRequest) ((GetRequest) ((GetRequest) a.a(com.example.gita.gxty.a.a("center/editSign")).tag(this)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<String>>(this, this.a) {
                        final /* synthetic */ AnonymousClass3 a;

                        public void a(com.lzy.okgo.model.a<LzyResponse<String>> aVar) {
                            super.a((com.lzy.okgo.model.a) aVar);
                            s.b(((LzyResponse) aVar.c()).msg);
                            q.a(this.a.a.c()).a("signature", this.a.a.signature.getText().toString());
                            Intent intent = new Intent();
                            intent.putExtra("signature", this.a.a.signature.getText().toString());
                            this.a.a.setResult(-1, intent);
                            this.a.a.finish();
                        }
                    });
                }
                return false;
            }
        });
        this.signature.setHorizontallyScrolling(false);
        this.signature.setMaxLines(Integer.MAX_VALUE);
    }

    protected int a() {
        return R.layout.activity_signature;
    }
}
