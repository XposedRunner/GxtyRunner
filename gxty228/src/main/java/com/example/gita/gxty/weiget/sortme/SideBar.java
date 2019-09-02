package com.example.gita.gxty.weiget.sortme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.example.gita.gxty.R;

public class SideBar extends View {
    private a a;
    private String[] b;
    private int c;
    private Paint d;
    private TextView e;

    public interface a {
        void a(String str);
    }

    public void setTextView(TextView textView) {
        this.e = textView;
    }

    public SideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.c = -1;
        this.d = new Paint();
    }

    public SideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.c = -1;
        this.d = new Paint();
    }

    public SideBar(Context context) {
        super(context);
        this.b = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.c = -1;
        this.d = new Paint();
    }

    public void setLetters(String[] strArr) {
        this.b = strArr;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / this.b.length;
        int parseColor = Color.parseColor("#999999");
        int parseColor2 = Color.parseColor("#4bd9ba");
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sidebar_textsize);
        for (height = 0; height < this.b.length; height++) {
            this.d.setColor(parseColor);
            this.d.setAntiAlias(true);
            this.d.setTextSize((float) dimensionPixelSize);
            if (height == this.c) {
                this.d.setColor(parseColor2);
                this.d.setFakeBoldText(true);
            }
            canvas.drawText(this.b[height], ((float) (width / 2)) - (this.d.measureText(this.b[height]) / 2.0f), (float) ((length * height) + length), this.d);
            this.d.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.c;
        a aVar = this.a;
        int height = (int) ((y / ((float) getHeight())) * ((float) this.b.length));
        switch (action) {
            case 1:
                setBackgroundDrawable(new ColorDrawable(0));
                this.c = -1;
                invalidate();
                if (this.e != null) {
                    this.e.setVisibility(4);
                    break;
                }
                break;
            default:
                setBackgroundDrawable(new ColorDrawable(0));
                if (i != height && height >= 0 && height < this.b.length) {
                    if (aVar != null) {
                        aVar.a(this.b[height]);
                    }
                    if (this.e != null) {
                        this.e.setText(this.b[height]);
                        this.e.setVisibility(0);
                    }
                    this.c = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(a aVar) {
        this.a = aVar;
    }
}
