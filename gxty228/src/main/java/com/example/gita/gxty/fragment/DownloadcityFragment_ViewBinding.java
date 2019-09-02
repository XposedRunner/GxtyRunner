package com.example.gita.gxty.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.gita.gxty.R;

public class DownloadcityFragment_ViewBinding implements Unbinder {
    private DownloadcityFragment a;
    private View b;
    private View c;

    @UiThread
    public DownloadcityFragment_ViewBinding(final DownloadcityFragment downloadcityFragment, View view) {
        this.a = downloadcityFragment;
        downloadcityFragment.mDownLoadedList = (ListView) Utils.findRequiredViewAsType(view, R.id.downloaded_map_list, "field 'mDownLoadedList'", ListView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.update, "method 'butterknifeOnItemClick'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ DownloadcityFragment_ViewBinding b;

            public void doClick(View view) {
                downloadcityFragment.butterknifeOnItemClick(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.del, "method 'butterknifeOnItemClick'");
        this.c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ DownloadcityFragment_ViewBinding b;

            public void doClick(View view) {
                downloadcityFragment.butterknifeOnItemClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        DownloadcityFragment downloadcityFragment = this.a;
        if (downloadcityFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.a = null;
        downloadcityFragment.mDownLoadedList = null;
        this.b.setOnClickListener(null);
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
