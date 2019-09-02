package com.chad.library.adapter.base;

import android.view.ViewGroup;
import com.chad.library.adapter.base.entity.SectionEntity;
import java.util.List;

public abstract class BaseSectionQuickAdapter<T extends SectionEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    protected static final int SECTION_HEADER_VIEW = 1092;
    protected int mSectionHeadResId;

    protected abstract void convertHead(K k, T t);

    public BaseSectionQuickAdapter(int i, int i2, List<T> list) {
        super(i, list);
        this.mSectionHeadResId = i2;
    }

    protected int getDefItemViewType(int i) {
        return ((SectionEntity) this.mData.get(i)).isHeader ? SECTION_HEADER_VIEW : 0;
    }

    protected K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        if (i == SECTION_HEADER_VIEW) {
            return createBaseViewHolder(getItemView(this.mSectionHeadResId, viewGroup));
        }
        return super.onCreateDefViewHolder(viewGroup, i);
    }

    protected boolean isFixedViewType(int i) {
        return super.isFixedViewType(i) || i == SECTION_HEADER_VIEW;
    }

    public void onBindViewHolder(K k, int i) {
        switch (k.getItemViewType()) {
            case SECTION_HEADER_VIEW /*1092*/:
                setFullSpan(k);
                convertHead(k, (SectionEntity) getItem(i - getHeaderLayoutCount()));
                return;
            default:
                super.onBindViewHolder((BaseViewHolder) k, i);
                return;
        }
    }
}
