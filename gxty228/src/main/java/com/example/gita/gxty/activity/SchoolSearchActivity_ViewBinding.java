package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class SchoolSearchActivity_ViewBinding implements Unbinder {
    private SchoolSearchActivity a;

    @UiThread
    public SchoolSearchActivity_ViewBinding(SchoolSearchActivity schoolSearchActivity, View view) {
        this.a = schoolSearchActivity;
        schoolSearchActivity.rv_listdown = (ListView) Utils.findRequiredViewAsType(view, R.id.rv_listdown, "field 'rv_listdown'", ListView.class);
        schoolSearchActivity.searchBtn = (EditText) Utils.findRequiredViewAsType(view, R.id.searchBtn, "field 'searchBtn'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        SchoolSearchActivity schoolSearchActivity = this.a;
        if (schoolSearchActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        schoolSearchActivity.rv_listdown = null;
        schoolSearchActivity.searchBtn = null;
    }
}
