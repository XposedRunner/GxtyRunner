package com.example.gita.gxty.weiget.gallery;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class RvGalleryAdapter<T, VH extends ViewHolder> extends Adapter<ViewHolder> {

    protected class EmptyView extends ViewHolder {
    }
}
