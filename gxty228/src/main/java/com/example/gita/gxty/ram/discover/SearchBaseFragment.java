package com.example.gita.gxty.ram.discover;

import android.support.v4.app.FragmentActivity;
import com.example.gita.gxty.activity.BaseFragment;

public class SearchBaseFragment extends BaseFragment {
    public String c;
    public int d = 1;

    public void b(String str) {
        this.c = str;
        this.d = 1;
        b(this.d);
    }

    public void b(int i) {
    }

    public String b() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SearchActivity) {
            return ((SearchActivity) activity).b();
        }
        return "";
    }

    public boolean c() {
        if (getActivity() instanceof SearchActivity) {
            return true;
        }
        return false;
    }
}
