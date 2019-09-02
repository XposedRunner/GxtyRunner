package com.example.gita.gxty.ram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.lzy.okgo.request.PostRequest;
import com.yuyh.library.imgsel.a;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig.Builder;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class BaseImgActivity extends BaseActivity {
    private String f;
    private Map<String, String> g = new HashMap();
    private Map<String, ImageView> h = new HashMap();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.a().a(new ImageLoader() {
            public void displayImage(Context context, String str, ImageView imageView) {
                e.b(context).a(str).a(imageView);
            }
        });
    }

    public void a(String str, ImageView imageView) {
        this.h.put(str, imageView);
    }

    public String c(String str) {
        return (String) this.g.get(str);
    }

    public boolean b() {
        return false;
    }

    public boolean o() {
        return true;
    }

    public int p() {
        return 400;
    }

    public int q() {
        return 400;
    }

    public int r() {
        return 1;
    }

    public String s() {
        return "图片";
    }

    public void d(String str) {
        boolean z = true;
        this.f = str;
        try {
            Builder builder = new Builder();
            if (r() <= 1) {
                z = false;
            }
            a.a().a(this, builder.multiSelect(z).rememberSelected(false).btnBgColor(-7829368).btnTextColor(-16776961).backResId(R.mipmap.back).title(s()).titleColor(-1).titleBgColor(Color.parseColor("#36363b")).cropSize(p(), q(), p(), q()).needCrop(b()).needCamera(o()).maxNum(r()).build(), 10010);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10010) {
            return;
        }
        if (i2 != -1 || intent == null) {
            a(this.f, false);
            return;
        }
        try {
            String str = (String) intent.getStringArrayListExtra("result").get(0);
            this.g.put(this.f, str);
            ImageView imageView = (ImageView) this.h.get(this.f);
            if (imageView != null) {
                e.a((FragmentActivity) this).a(new File(str)).a(imageView);
            }
            a(this.f, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, boolean z) {
    }

    public String e(String str) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            PostRequest postRequest = (PostRequest) ((PostRequest) ((PostRequest) com.lzy.okgo.a.b(com.example.gita.gxty.a.a("upload/do_upload")).tag(this)).isMultipart(true)).params("file", file);
            com.example.gita.gxty.a.a.a(postRequest.getHeaders());
            return new JSONObject(postRequest.execute().body().string()).getJSONObject("data").getJSONObject("upload_data").getString("uri");
        }
        throw new Exception("文件不存在");
    }
}
