package com.chad.library.adapter.base;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseViewHolder extends ViewHolder {
    private BaseQuickAdapter adapter;
    Object associatedObject;
    private final LinkedHashSet<Integer> childClickViewIds = new LinkedHashSet();
    @Deprecated
    public View convertView;
    private final LinkedHashSet<Integer> itemChildLongClickViewIds = new LinkedHashSet();
    private final HashSet<Integer> nestViews = new HashSet();
    private final SparseArray<View> views = new SparseArray();

    public Set<Integer> getNestViews() {
        return this.nestViews;
    }

    public BaseViewHolder(View view) {
        super(view);
        this.convertView = view;
    }

    private int getClickPosition() {
        if (getLayoutPosition() >= this.adapter.getHeaderLayoutCount()) {
            return getLayoutPosition() - this.adapter.getHeaderLayoutCount();
        }
        return 0;
    }

    public HashSet<Integer> getItemChildLongClickViewIds() {
        return this.itemChildLongClickViewIds;
    }

    public HashSet<Integer> getChildClickViewIds() {
        return this.childClickViewIds;
    }

    @Deprecated
    public View getConvertView() {
        return this.convertView;
    }

    public BaseViewHolder setText(@IdRes int i, CharSequence charSequence) {
        ((TextView) getView(i)).setText(charSequence);
        return this;
    }

    public BaseViewHolder setText(@IdRes int i, @StringRes int i2) {
        ((TextView) getView(i)).setText(i2);
        return this;
    }

    public BaseViewHolder setImageResource(@IdRes int i, @DrawableRes int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public BaseViewHolder setBackgroundColor(@IdRes int i, @ColorInt int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public BaseViewHolder setBackgroundRes(@IdRes int i, @DrawableRes int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public BaseViewHolder setTextColor(@IdRes int i, @ColorInt int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public BaseViewHolder setImageDrawable(@IdRes int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setImageBitmap(@IdRes int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public BaseViewHolder setAlpha(@IdRes int i, float f) {
        if (VERSION.SDK_INT >= 11) {
            getView(i).setAlpha(f);
        } else {
            Animation alphaAnimation = new AlphaAnimation(f, f);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            getView(i).startAnimation(alphaAnimation);
        }
        return this;
    }

    public BaseViewHolder setGone(@IdRes int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }

    public BaseViewHolder setVisible(@IdRes int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 4);
        return this;
    }

    public BaseViewHolder linkify(@IdRes int i) {
        Linkify.addLinks((TextView) getView(i), 15);
        return this;
    }

    public BaseViewHolder setTypeface(@IdRes int i, Typeface typeface) {
        TextView textView = (TextView) getView(i);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | 128);
        return this;
    }

    public BaseViewHolder setTypeface(Typeface typeface, int... iArr) {
        for (int view : iArr) {
            TextView textView = (TextView) getView(view);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public BaseViewHolder setProgress(@IdRes int i, int i2) {
        ((ProgressBar) getView(i)).setProgress(i2);
        return this;
    }

    public BaseViewHolder setProgress(@IdRes int i, int i2, int i3) {
        ProgressBar progressBar = (ProgressBar) getView(i);
        progressBar.setMax(i3);
        progressBar.setProgress(i2);
        return this;
    }

    public BaseViewHolder setMax(@IdRes int i, int i2) {
        ((ProgressBar) getView(i)).setMax(i2);
        return this;
    }

    public BaseViewHolder setRating(@IdRes int i, float f) {
        ((RatingBar) getView(i)).setRating(f);
        return this;
    }

    public BaseViewHolder setRating(@IdRes int i, float f, int i2) {
        RatingBar ratingBar = (RatingBar) getView(i);
        ratingBar.setMax(i2);
        ratingBar.setRating(f);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnClickListener(@IdRes int i, OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public BaseViewHolder addOnClickListener(@IdRes int i) {
        this.childClickViewIds.add(Integer.valueOf(i));
        View view = getView(i);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (BaseViewHolder.this.adapter.getOnItemChildClickListener() != null) {
                        BaseViewHolder.this.adapter.getOnItemChildClickListener().onItemChildClick(BaseViewHolder.this.adapter, view, BaseViewHolder.this.getClickPosition());
                    }
                }
            });
        }
        return this;
    }

    public BaseViewHolder setNestView(@IdRes int i) {
        addOnClickListener(i);
        addOnLongClickListener(i);
        this.nestViews.add(Integer.valueOf(i));
        return this;
    }

    public BaseViewHolder addOnLongClickListener(@IdRes int i) {
        this.itemChildLongClickViewIds.add(Integer.valueOf(i));
        View view = getView(i);
        if (view != null) {
            if (!view.isLongClickable()) {
                view.setLongClickable(true);
            }
            view.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return BaseViewHolder.this.adapter.getOnItemChildLongClickListener() != null && BaseViewHolder.this.adapter.getOnItemChildLongClickListener().onItemChildLongClick(BaseViewHolder.this.adapter, view, BaseViewHolder.this.getClickPosition());
                }
            });
        }
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnTouchListener(@IdRes int i, OnTouchListener onTouchListener) {
        getView(i).setOnTouchListener(onTouchListener);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnLongClickListener(@IdRes int i, OnLongClickListener onLongClickListener) {
        getView(i).setOnLongClickListener(onLongClickListener);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnItemClickListener(@IdRes int i, OnItemClickListener onItemClickListener) {
        ((AdapterView) getView(i)).setOnItemClickListener(onItemClickListener);
        return this;
    }

    public BaseViewHolder setOnItemLongClickListener(@IdRes int i, OnItemLongClickListener onItemLongClickListener) {
        ((AdapterView) getView(i)).setOnItemLongClickListener(onItemLongClickListener);
        return this;
    }

    public BaseViewHolder setOnItemSelectedClickListener(@IdRes int i, OnItemSelectedListener onItemSelectedListener) {
        ((AdapterView) getView(i)).setOnItemSelectedListener(onItemSelectedListener);
        return this;
    }

    public BaseViewHolder setOnCheckedChangeListener(@IdRes int i, OnCheckedChangeListener onCheckedChangeListener) {
        ((CompoundButton) getView(i)).setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    public BaseViewHolder setTag(@IdRes int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public BaseViewHolder setTag(@IdRes int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public BaseViewHolder setChecked(@IdRes int i, boolean z) {
        View view = getView(i);
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(z);
        }
        return this;
    }

    public BaseViewHolder setAdapter(@IdRes int i, Adapter adapter) {
        ((AdapterView) getView(i)).setAdapter(adapter);
        return this;
    }

    protected BaseViewHolder setAdapter(BaseQuickAdapter baseQuickAdapter) {
        this.adapter = baseQuickAdapter;
        return this;
    }

    public <T extends View> T getView(@IdRes int i) {
        View view = (View) this.views.get(i);
        if (view != null) {
            return view;
        }
        T findViewById = this.itemView.findViewById(i);
        this.views.put(i, findViewById);
        return findViewById;
    }

    public Object getAssociatedObject() {
        return this.associatedObject;
    }

    public void setAssociatedObject(Object obj) {
        this.associatedObject = obj;
    }
}
