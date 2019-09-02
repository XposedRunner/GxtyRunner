package com.example.gita.gxty.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.github.mikephil.charting.utils.Utils;

/* compiled from: MyTextWatcher */
public class j implements TextWatcher {
    private EditText a;
    private int b;
    private int c;
    private int d;
    private a e;

    /* compiled from: MyTextWatcher */
    public interface a {
        void a(long j);
    }

    public j(EditText editText, int i) {
        this(editText, i, null);
    }

    public j(EditText editText, int i, a aVar) {
        this.b = 0;
        this.a = editText;
        this.b = i;
        this.e = aVar;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        try {
            this.c = this.a.getSelectionStart();
            this.d = this.a.getSelectionEnd();
            this.a.removeTextChangedListener(this);
            while (a(editable.toString()) > ((long) this.b)) {
                editable.delete(this.c - 1, this.d);
                this.c--;
                this.d--;
            }
            this.a.setSelection(this.c);
            this.a.addTextChangedListener(this);
            if (this.e != null) {
                this.e.a(a(this.a.getText().toString().trim()));
            }
        } catch (Exception e) {
            h.a(e);
        }
    }

    public static long a(CharSequence charSequence) {
        double d = Utils.DOUBLE_EPSILON;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (charAt <= '\u0000' || charAt >= '') {
                d += 1.0d;
            } else {
                d += 0.5d;
            }
        }
        return Math.round(d);
    }
}
