package com.example.gita.gxty.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.Feed;
import com.example.gita.gxty.model.XingKongAds;
import com.example.gita.gxty.ram.discover.DiscoverActivity;
import com.example.gita.gxty.utils.d;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.s;
import com.example.gita.gxty.weiget.ninegrid.NineGridTestLayout;
import com.qq.e.ads.nativ.NativeExpressADView;
import java.util.List;

public class FeedAdAdapter extends BaseQuickAdapter<Feed, BaseViewHolder> {
    BaseActivity a;

    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (Feed) obj);
    }

    public FeedAdAdapter(List list, BaseActivity baseActivity) {
        super(R.layout.item_feed, list);
        this.a = baseActivity;
    }

    public void a(NativeExpressADView nativeExpressADView, int i, boolean z) {
        try {
            Feed feed = new Feed();
            feed.isNeAd = 1;
            feed.adView = nativeExpressADView;
            if (z) {
                h.b("998800-1");
                addData((Object) feed);
            } else if (getItemCount() < i + 2) {
                h.b("998800-2=" + getItemCount());
                addData((Object) feed);
            } else {
                h.b("998800-3");
                addData(i + 2, (Object) feed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(XingKongAds xingKongAds, int i, boolean z) {
        if (xingKongAds != null) {
            try {
                if (xingKongAds.ad_type == 1) {
                    Feed feed = new Feed();
                    feed.isNeAd = 2;
                    feed.user_name = "微摇传媒";
                    feed.content_word = xingKongAds.title + "  " + xingKongAds.desc;
                    feed.user_head = "http://wiki.hanfupay.cn/img/%E6%98%9F%E6%B2%B3.png";
                    feed.content_pic = xingKongAds.image_urls;
                    feed.create_time = System.currentTimeMillis() + "";
                    feed.xkAds = xingKongAds;
                    if (z) {
                        h.b("998800-1");
                        addData((Object) feed);
                    } else if (getItemCount() < i + 2) {
                        h.b("998800-2=" + getItemCount());
                        addData((Object) feed);
                    } else {
                        h.b("998800-3");
                        addData(i + 2, (Object) feed);
                    }
                    DiscoverActivity.a(xingKongAds.show_urls);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void a(final BaseViewHolder baseViewHolder, final Feed feed) {
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.itemLayout);
        LinearLayout linearLayout2 = (LinearLayout) baseViewHolder.getView(R.id.adLayout);
        if (feed.isNeAd == 1) {
            try {
                linearLayout2.removeAllViews();
                feed.adView.render();
                if (feed.adView.getParent() != null) {
                    ((ViewGroup) feed.adView.getParent()).removeAllViews();
                }
                linearLayout2.addView(feed.adView);
                linearLayout2.setVisibility(0);
                linearLayout.setVisibility(8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (feed.isNeAd == 0) {
            linearLayout2.setVisibility(8);
            linearLayout.setVisibility(0);
            linearLayout2.removeAllViews();
            baseViewHolder.setText((int) R.id.nickName, feed.user_name);
            baseViewHolder.setText((int) R.id.timeTxt, d.e(feed.getMyabcdTime()));
            baseViewHolder.setText((int) R.id.content, s.a(feed.content_word));
            baseViewHolder.setText((int) R.id.liulanTxt, feed.view_count + "");
            baseViewHolder.setText((int) R.id.pinglunTxt, feed.feed_count + "");
            baseViewHolder.setText((int) R.id.zanTxt, feed.like_count + "");
            baseViewHolder.getView(R.id.cclayout).setVisibility(0);
            baseViewHolder.getView(R.id.timeTxt).setVisibility(0);
            e.b(this.mContext).a(feed.user_head).b((int) R.mipmap.default_avatar).a((ImageView) baseViewHolder.getView(R.id.imageView));
            r0 = (NineGridTestLayout) baseViewHolder.getView(R.id.nineGrid);
            r0.setIsShowAll(false);
            r0.setUrlList(feed.content_pic);
            r0.a(true);
            r0.setCanClick(true);
            baseViewHolder.setOnClickListener(R.id.zanlayout, new OnClickListener(this) {
                final /* synthetic */ FeedAdAdapter c;

                public void onClick(View view) {
                    BaseActivity.a(this.c.a, feed.feed_id, feed.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
                }
            });
        } else if (feed.isNeAd == 2) {
            linearLayout2.setVisibility(8);
            linearLayout.setVisibility(0);
            linearLayout2.removeAllViews();
            baseViewHolder.setText((int) R.id.nickName, feed.user_name);
            baseViewHolder.setText((int) R.id.content, s.a(feed.content_word));
            baseViewHolder.getView(R.id.cclayout).setVisibility(8);
            baseViewHolder.getView(R.id.timeTxt).setVisibility(8);
            ((ImageView) baseViewHolder.getView(R.id.imageView)).setImageResource(R.mipmap.ads_user);
            r0 = (NineGridTestLayout) baseViewHolder.getView(R.id.nineGrid);
            r0.setIsShowAll(false);
            r0.setUrlList(feed.content_pic);
            r0.a(false);
            r0.setCanClick(false);
            baseViewHolder.setOnClickListener(R.id.zanlayout, new OnClickListener(this) {
                final /* synthetic */ FeedAdAdapter c;

                public void onClick(View view) {
                    BaseActivity.a(this.c.a, feed.feed_id, feed.like_count, (TextView) baseViewHolder.getView(R.id.zanTxt), (ImageView) baseViewHolder.getView(R.id.zanImg));
                }
            });
        }
    }
}
