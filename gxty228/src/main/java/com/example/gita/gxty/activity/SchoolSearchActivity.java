package com.example.gita.gxty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.example.gita.gxty.R;
import com.example.gita.gxty.adapter.SchoolAdapter;
import com.example.gita.gxty.model.School;
import com.example.gita.gxty.utils.r;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchoolSearchActivity extends BaseActivity {
    private List<School> f = new ArrayList();
    private List<School> g = new ArrayList();
    private SchoolAdapter h;
    @BindView(2131755228)
    protected ListView rv_listdown;
    @BindView(2131755426)
    protected EditText searchBtn;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        List list = (List) getIntent().getSerializableExtra("data");
        if (!(list == null || list.isEmpty())) {
            this.f.addAll(list);
            this.g.addAll(list);
        }
        this.h = new SchoolAdapter(c(), null, this.f, false);
        this.rv_listdown.setAdapter(this.h);
        this.rv_listdown.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SchoolSearchActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    Intent intent = new Intent();
                    intent.putExtra("school", (Serializable) this.a.f.get(i));
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.searchBtn.setImeOptions(3);
        this.searchBtn.setInputType(1);
        this.searchBtn.setSingleLine(true);
        this.searchBtn.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ SchoolSearchActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3) {
                    return false;
                }
                BaseActivity.a(this.a.searchBtn);
                this.a.b();
                return true;
            }
        });
        this.searchBtn.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SchoolSearchActivity a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void b() {
        CharSequence trim = this.searchBtn.getText().toString().trim();
        if (r.a(trim)) {
            this.f.clear();
            this.f.addAll(this.g);
        } else {
            Collection arrayList = new ArrayList();
            for (School school : this.g) {
                if (school.name.contains(trim)) {
                    arrayList.add(school);
                }
            }
            this.f.clear();
            this.f.addAll(arrayList);
        }
        this.h.a(this.f);
    }

    protected int a() {
        return R.layout.activity_school_search;
    }
}
