package com.example.gita.gxty.activity;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    public static int a = 10;

    public static String a(int i) {
        return "p=" + i + "&s=" + a;
    }

    public BaseActivity a() {
        return (BaseActivity) getActivity();
    }

    public void a(String str) {
        a().a(str);
    }
}
