package com.example.gita.gxty.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.weiget.sortme.SideBar;

public class SchoollistActivity_ViewBinding implements Unbinder {
    private SchoollistActivity a;

    @UiThread
    public SchoollistActivity_ViewBinding(SchoollistActivity schoollistActivity, View view) {
        this.a = schoollistActivity;
        schoollistActivity.sideBar = (SideBar) Utils.findRequiredViewAsType(view, R.id.sideBar, "field 'sideBar'", SideBar.class);
        schoollistActivity.rv_listup = (ListView) Utils.findRequiredViewAsType(view, R.id.rv_listup, "field 'rv_listup'", ListView.class);
        schoollistActivity.rv_listdown = (ListView) Utils.findRequiredViewAsType(view, R.id.rv_listdown, "field 'rv_listdown'", ListView.class);
        schoollistActivity.addressBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.addressBtn, "field 'addressBtn'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SchoollistActivity schoollistActivity = this.a;
        if (schoollistActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        schoollistActivity.sideBar = null;
        schoollistActivity.rv_listup = null;
        schoollistActivity.rv_listdown = null;
        schoollistActivity.addressBtn = null;
    }
}
