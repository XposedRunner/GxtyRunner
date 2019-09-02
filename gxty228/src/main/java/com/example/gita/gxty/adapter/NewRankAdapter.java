package com.example.gita.gxty.adapter;

import android.widget.ImageView;
import com.bumptech.glide.e;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.model.DataBean;
import com.example.gita.gxty.model.DataRankLike;
import com.example.gita.gxty.model.LzyResponse;
import com.example.gita.gxty.model.NewRank;
import com.example.gita.gxty.ram.discover.RankActivity;
import com.example.gita.gxty.utils.b;
import com.like.LikeButton;
import com.like.c;
import com.lzy.okgo.a;
import com.lzy.okgo.request.PostRequest;
import java.util.ArrayList;

public class NewRankAdapter extends BaseQuickAdapter<NewRank, BaseViewHolder> {
    private String a;
    private BaseActivity b;

    protected /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
        a(baseViewHolder, (NewRank) obj);
    }

    public NewRankAdapter(BaseActivity baseActivity, ArrayList arrayList, String str) {
        super(R.layout.item_new_rank, arrayList);
        this.a = str;
        this.b = baseActivity;
    }

    protected void a(final BaseViewHolder baseViewHolder, final NewRank newRank) {
        final String str = this.a + newRank.user_id;
        baseViewHolder.setText((int) R.id.noTxt, baseViewHolder.getAdapterPosition() + "");
        baseViewHolder.setText((int) R.id.nameTxt, newRank.user_name);
        baseViewHolder.setText((int) R.id.schoolTxt, newRank.desc);
        baseViewHolder.setText((int) R.id.kmTxt, newRank.km);
        baseViewHolder.setText((int) R.id.likeTxt, a(newRank.praised));
        e.b(this.mContext).a(newRank.user_head).b((int) R.mipmap.default_avatar).a((ImageView) baseViewHolder.getView(R.id.avatarImg));
        LikeButton likeButton = (LikeButton) baseViewHolder.getView(R.id.zanButton);
        likeButton.setLiked(Boolean.valueOf("1".equals((String) RankActivity.k.get(str))));
        likeButton.setOnLikeListener(new c(this) {
            final /* synthetic */ NewRankAdapter d;

            public void a(LikeButton likeButton) {
                this.d.a(this.d.b, str, newRank, baseViewHolder);
            }

            public void b(LikeButton likeButton) {
                this.d.a(this.d.b, str, newRank, baseViewHolder);
            }
        });
    }

    private String a(int i) {
        if (i < 1000) {
            return i + "";
        }
        if (i > 99999) {
            return "99k+";
        }
        return (i / 1000) + "." + ((i % 1000) / 100) + "k";
    }

    public void a(BaseActivity baseActivity, String str, NewRank newRank, BaseViewHolder baseViewHolder) {
        String str2 = "1";
        final String str3 = (String) RankActivity.k.get(str);
        if ("1".equals(str3)) {
            str2 = "2";
        } else {
            str2 = "1";
        }
        final Object dataRankLike = new DataRankLike();
        dataRankLike.from = "1";
        dataRankLike.type = str2;
        dataRankLike.toUserId = newRank.user_id;
        DataBean a = b.a(dataRankLike);
        final String str4 = str;
        final NewRank newRank2 = newRank;
        final BaseViewHolder baseViewHolder2 = baseViewHolder;
        ((PostRequest) ((PostRequest) ((PostRequest) a.b(com.example.gita.gxty.a.a("center/doLike")).tag(baseActivity)).params("sign", a.sign, new boolean[0])).params("data", a.data, new boolean[0])).execute(new com.example.gita.gxty.a.a<LzyResponse<Void>>(this, baseActivity, false) {
            final /* synthetic */ NewRankAdapter f;

            public void a(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                super.a((com.lzy.okgo.model.a) aVar);
                RankActivity.k.put(str4, dataRankLike.type);
                if ("1".equals(dataRankLike.type)) {
                    newRank2.praised++;
                } else {
                    newRank2.praised--;
                }
                baseViewHolder2.setText((int) R.id.likeTxt, this.f.a(newRank2.praised) + "");
            }

            public void b(com.lzy.okgo.model.a<LzyResponse<Void>> aVar) {
                RankActivity.k.put(str4, str3);
            }
        });
    }
}
