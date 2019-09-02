package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.bumptech.glide.g;
import java.util.HashSet;

public class SupportRequestManagerFragment extends Fragment {
    private g a;
    private final a b;
    private final l c;
    private final HashSet<SupportRequestManagerFragment> d;
    private SupportRequestManagerFragment e;

    private class a implements l {
        final /* synthetic */ SupportRequestManagerFragment a;

        private a(SupportRequestManagerFragment supportRequestManagerFragment) {
            this.a = supportRequestManagerFragment;
        }
    }

    public SupportRequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(a aVar) {
        this.c = new a();
        this.d = new HashSet();
        this.b = aVar;
    }

    public void a(g gVar) {
        this.a = gVar;
    }

    a a() {
        return this.b;
    }

    public g b() {
        return this.a;
    }

    public l c() {
        return this.c;
    }

    private void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.add(supportRequestManagerFragment);
    }

    private void b(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.remove(supportRequestManagerFragment);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.e = k.a().a(getActivity().getSupportFragmentManager());
            if (this.e != this) {
                this.e.a(this);
            }
        } catch (Throwable e) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.a != null) {
            this.a.a();
        }
    }
}
