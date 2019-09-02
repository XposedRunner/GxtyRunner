package com.example.gita.gxty.ram;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.TitleBar;

public class DataActivity_ViewBinding implements Unbinder {
    private DataActivity a;

    @UiThread
    public DataActivity_ViewBinding(DataActivity dataActivity, View view) {
        this.a = dataActivity;
        dataActivity.title_bar = (TitleBar) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'title_bar'", TitleBar.class);
        dataActivity.rv_listdown = (ListView) Utils.findRequiredViewAsType(view, R.id.rv_listdown, "field 'rv_listdown'", ListView.class);
        dataActivity.dataSizeText = (TextView) Utils.findRequiredViewAsType(view, R.id.dataSizeText, "field 'dataSizeText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        DataActivity dataActivity = this.a;
        if (dataActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        dataActivity.title_bar = null;
        dataActivity.rv_listdown = null;
        dataActivity.dataSizeText = null;
    }
}
