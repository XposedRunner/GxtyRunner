package com.duudu.image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.jiguang.net.HttpUtils;
import com.example.gita.gxty.R;
import com.example.gita.gxty.activity.BaseActivity;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.aletview.AlertView;
import com.example.gita.gxty.weiget.aletview.AlertView.Style;
import com.example.gita.gxty.weiget.aletview.c;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.e;
import com.github.chrisbanes.photoview.f;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageViewPagerActivity extends BaseActivity {
    private HackyViewPager f = null;
    private List<String> g = new ArrayList();
    private a h = null;
    private TextView i;
    private int j;
    private int k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private Handler o = new Handler(this) {
        final /* synthetic */ ImageViewPagerActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.a("保存图片:" + ((String) message.obj));
                this.a.g();
            } else if (message.what == 2) {
                this.a.f();
            }
        }
    };

    class a extends PagerAdapter {
        public Map<Integer, View> a;
        final /* synthetic */ ImageViewPagerActivity b;

        public a(ImageViewPagerActivity imageViewPagerActivity) {
            this.b = imageViewPagerActivity;
            this.a = null;
            this.a = new HashMap();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        public int getCount() {
            return this.b.g.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            String str = (String) this.b.g.get(i);
            View view = (View) this.a.get(Integer.valueOf(i));
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.v_image_pager_item, null);
                this.a.put(Integer.valueOf(i), view);
                viewGroup.addView(view);
            }
            View view2 = view;
            ImageView imageView = (PhotoView) view2.findViewById(R.id.imageView);
            imageView.setOnPhotoTapListener(new f(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(ImageView imageView, float f, float f2) {
                    this.a.b.doBack(null);
                }
            });
            imageView.setOnOutsidePhotoTapListener(new e(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(ImageView imageView) {
                    this.a.b.doBack(null);
                }
            });
            imageView.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    if (this.a.b.n) {
                        this.a.b.p();
                    }
                    return true;
                }
            });
            final ProgressBar progressBar = (ProgressBar) view2.findViewById(R.id.loading);
            d.a().a(str, imageView, new com.nostra13.universalimageloader.core.c.a().a((int) R.mipmap.default_feed).b((int) R.mipmap.default_feed).a(true).b(true).c(true).a(com.nostra13.universalimageloader.core.a.c()).a(ImageScaleType.NONE).a(), new com.nostra13.universalimageloader.core.d.a(this) {
                final /* synthetic */ a b;

                public void a(String str, View view) {
                    progressBar.setVisibility(0);
                }

                public void a(String str, View view, FailReason failReason) {
                    progressBar.setVisibility(8);
                }

                public void a(String str, View view, Bitmap bitmap) {
                    progressBar.setVisibility(8);
                    this.b.b.n = true;
                }

                public void b(String str, View view) {
                    progressBar.setVisibility(8);
                }
            });
            return view2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    protected int a() {
        return R.layout.ba_image_viewpager_main;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Collection stringArrayListExtra = getIntent().getStringArrayListExtra("first");
        this.j = getIntent().getIntExtra("second", 0);
        this.l = getIntent().getBooleanExtra("needAnima", false);
        this.m = getIntent().getBooleanExtra("needTitleBar", false);
        this.k = getIntent().getIntExtra("defaultImgId", R.mipmap.default_feed);
        if (stringArrayListExtra != null) {
            this.g.addAll(stringArrayListExtra);
        }
        this.i = (TextView) findViewById(R.id.currentTxt);
        o();
        this.f = (HackyViewPager) findViewById(R.id.viewPager);
        this.h = new a(this);
        this.f.setAdapter(this.h);
        this.f.setCurrentItem(this.j);
        this.f.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ ImageViewPagerActivity a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.j = i;
                this.a.o();
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void o() {
        this.i.setText((this.j + 1) + HttpUtils.PATHS_SEPARATOR + this.g.size());
    }

    public static void a(Activity activity, List<String> list, int i, boolean z) {
        a(activity, list, i, z, false);
    }

    public static void a(Activity activity, List<String> list, int i, boolean z, boolean z2) {
        try {
            List arrayList = new ArrayList();
            for (String str : list) {
                try {
                    if (str.startsWith(HttpUtils.PATHS_SEPARATOR)) {
                        arrayList.add("file://" + str);
                    } else {
                        arrayList.add(str);
                    }
                } catch (Exception e) {
                    h.a(e);
                }
            }
            Intent intent = new Intent(activity, ImageViewPagerActivity.class);
            intent.putExtra("first", (Serializable) arrayList);
            intent.putExtra("second", i);
            intent.putExtra("needAnima", z);
            intent.putExtra("needTitleBar", z2);
            activity.startActivity(intent);
            if (z) {
                a(activity, true);
            }
        } catch (Exception e2) {
            h.a(e2);
        }
    }

    private void p() {
        String str = null;
        new AlertView(null, str, "取消", null, new String[]{"保存图片"}, this, Style.ActionSheet, new c(this) {
            final /* synthetic */ ImageViewPagerActivity a;

            {
                this.a = r1;
            }

            public void a(Object obj, int i) {
                try {
                    ((AlertView) obj).h();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i == 0) {
                    this.a.b();
                }
            }
        }).e();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1 && intent != null) {
            this.f.setCurrentItem(intent.getIntExtra("first", 0));
        }
    }

    public void doBack(View view) {
        finish();
        if (this.l) {
            a((Activity) this, false);
        }
    }

    public static void a(Activity activity, boolean z) {
        if (z) {
            activity.overridePendingTransition(R.anim.zoom_enter, R.anim.hold_anim);
        } else {
            activity.overridePendingTransition(R.anim.hold_anim, R.anim.zoom_exit);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        doBack(null);
        return true;
    }

    public void b() {
        this.o.sendEmptyMessage(2);
        new Thread(this) {
            final /* synthetic */ ImageViewPagerActivity a;

            {
                this.a = r1;
            }

            public void run() {
                OutputStream fileOutputStream;
                String str = (String) this.a.g.get(this.a.f.getCurrentItem());
                File file = new File(Environment.getExternalStorageDirectory(), "高校体育");
                if (!file.exists()) {
                    file.mkdir();
                }
                String str2 = "image_" + System.currentTimeMillis() + ".jpg";
                Bitmap a = d.a().a(str);
                File file2 = new File(file, str2);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    fileOutputStream = null;
                }
                try {
                    a.compress(CompressFormat.PNG, 100, fileOutputStream);
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    String absolutePath = file2.getAbsolutePath();
                    try {
                        Media.insertImage(this.a.getContentResolver(), absolutePath, str2, null);
                    } catch (FileNotFoundException e3) {
                        e3.printStackTrace();
                    }
                    this.a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + absolutePath)));
                    this.a.o.sendMessage(this.a.o.obtainMessage(1, str2));
                } catch (Exception e4) {
                    h.a(e4);
                }
            }
        }.start();
    }
}
