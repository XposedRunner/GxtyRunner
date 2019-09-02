package com.example.gita.gxty.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.Feed;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.ninegrid.NineGridTestLayout;
import java.util.List;

public class FeedAdapter extends BaseQuickAdapter<Feed, BaseViewHolder> {
    BaseActivity a;

    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Feed) obj);
    }

    public FeedAdapter(List list, BaseActivity baseActivity) {
        super(R.layout.item_feed, list);
        this.a = baseActivity;
    }

    protected void a(final BaseViewHolder baseViewHolder, final Feed feed) {
        baseViewHolder.setText((int) R.id.nickName, feed.user_name);
        baseViewHolder.setText((int) R.id.timeTxt, d.e(feed.getMyabcdTime()));
        baseViewHolder.setText((int) R.id.content, s.a(feed.content_word));
        baseViewHolder.setText((int) R.id.liulanTxt, feed.view_count + "");
        baseViewHolder.setText((int) R.id.pinglunTxt, feed.feed_count + "");
        baseViewHolder.setText((int) R.id.zanTxt, feed.like_count + "");
        e.b(this.mContext).a(feed.user_head).a((int) R.mipmap.default_avatar).b((int) R.mipmap.default_avatar).a((ImageView) baseViewHolder.getView(R.id.imageView));
        NineGridTestLayout nineGridTestLayout = (NineGridTestLayout) baseViewHolder.getView(R.id.nineGrid);
        nineGridTestLayout.setIsShowAll(false);
        nineGridTestLayout.setUrlList(feed.content_pic);
        baseViewHolder.setOnClickListener(R.id.zanlayout, new OnClickListener(this) {
            final /* synthetic */ FeedAdapter c;

            public void onClick(View view) {
                BaseActivity.a(this.c.a, feed.feed_id, feed.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
            }
        });
    }
}
