package com.example.gita.gxty.ram;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.e;
import com.example.gita.gxty.R;
import com.example.gita.gxty.ram.adapt.IAdapter;
import com.example.gita.gxty.ram.adapt.ImageItem;
import com.example.gita.gxty.ram.adapt.c;
import com.example.gita.gxty.ram.adapt.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AddPreImageView */
public class a {
    GridView a;
    Activity b;
    boolean c = true;
    boolean d = true;
    b e;
    List<String> f = new ArrayList();
    private c g = new c(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(View view, int i, int i2, int i3) {
            String str = (String) this.a.f.get(i);
            if (R.id.delete1 == view.getId()) {
                if (view != null) {
                    this.a.f.remove(i);
                    if (this.a.f.size() < 9 && !this.a.f.contains("add_btn_string")) {
                        this.a.f.add("add_btn_string");
                    }
                    this.a.d();
                }
            } else if ("add_btn_string".equals(str)) {
                if (this.a.e != null) {
                    this.a.e.a(view);
                }
            } else if (this.a.f.contains("add_btn_string")) {
                List arrayList = new ArrayList();
                arrayList.addAll(this.a.f);
                arrayList.remove("add_btn_string");
            }
        }
    };

    /* compiled from: AddPreImageView */
    public interface b {
        void a(View view);
    }

    /* compiled from: AddPreImageView */
    private class a extends IAdapter<List<String>> {
        final /* synthetic */ a a;

        public a(a aVar, Context context, c cVar, List<String> list) {
            this.a = aVar;
            super(context, cVar, list);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) this.b.get(i);
            if (view == null) {
                view = this.e.inflate(R.layout.a_post_add_image, null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.camara1);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.delete1);
            if (this.a.c) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            if ("add_btn_string".equals(str)) {
                imageView2.setVisibility(8);
                imageView.setImageResource(R.mipmap.addimg_8);
            } else {
                e.a(this.a.b).a("file://" + str).a(imageView);
            }
            d dVar = new d(i);
            a(view, dVar);
            a(view.findViewById(R.id.delete1), dVar);
            return view;
        }
    }

    public a(GridView gridView, Activity activity) {
        this.a = gridView;
        this.b = activity;
        this.f.add("add_btn_string");
        d();
    }

    public void setListener(b bVar) {
        this.e = bVar;
    }

    public void a(List<String> list, boolean z) {
        this.d = z;
        if (z) {
            int c = c();
            if (c > 0) {
                if (list.size() >= c) {
                    this.f.remove("add_btn_string");
                    list.subList(0, c).addAll(this.f);
                    this.f = list;
                } else {
                    this.f.remove("add_btn_string");
                    list.addAll(this.f);
                    list.add("add_btn_string");
                    this.f = list;
                }
                d();
                return;
            }
            return;
        }
        this.f.clear();
        if (list.size() < 9) {
            this.f.addAll(list);
            this.f.add("add_btn_string");
        } else {
            this.f.addAll(list.subList(0, 9));
        }
        d();
    }

    private void d() {
        this.a.setAdapter(new a(this, this.b, this.g, this.f));
    }

    public List<ImageItem> a() {
        List<ImageItem> arrayList = new ArrayList();
        int i = 0;
        for (String str : this.f) {
            if (!"add_btn_string".equals(str)) {
                arrayList.add(new ImageItem(str, null, i));
            }
            i++;
        }
        return arrayList;
    }

    public int b() {
        if (this.f.contains("add_btn_string")) {
            return this.f.size() - 1;
        }
        return this.f.size();
    }

    public int c() {
        if (this.d) {
            return 9 - b();
        }
        return 9;
    }
}
