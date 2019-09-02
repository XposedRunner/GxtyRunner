package com.chad.library.adapter.base;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.List;

public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    public static final int TYPE_NOT_FOUND = -404;
    private SparseIntArray layouts;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super((List) list);
    }

    protected int getDefItemViewType(int i) {
        Object obj = this.mData.get(i);
        if (obj instanceof MultiItemEntity) {
            return ((MultiItemEntity) obj).getItemType();
        }
        return DEFAULT_VIEW_TYPE;
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int i) {
        addItemType(DEFAULT_VIEW_TYPE, i);
    }

    protected K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(viewGroup, getLayoutId(i));
    }

    private int getLayoutId(int i) {
        return this.layouts.get(i, TYPE_NOT_FOUND);
    }

    protected void addItemType(int i, @LayoutRes int i2) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(i, i2);
    }
}
