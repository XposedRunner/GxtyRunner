package com.example.gita.gxty.ram.db.a;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import com.example.gita.gxty.utils.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DataResolver */
public class b extends com.example.gita.gxty.ram.db.b {
    public static final StringBuffer a = new StringBuffer().append("CREATE TABLE IF NOT EXISTS ").append("datatb").append("( ").append("itemid").append(" INTEGER PRIMARY KEY AUTOINCREMENT,").append("data1").append(" text,").append("data2").append(" text,").append("data3").append(" text,").append("data4").append(" text,").append("data5").append(" text,").append("data6").append(" text,").append("data7").append(" text,").append("data8").append(" text );");
    private ContentResolver b = null;

    public b(Context context) {
        this.b = context.getContentResolver();
    }

    public List<a> a() {
        List<a> arrayList = new ArrayList();
        try {
            Cursor query = this.b.query(a("datatb"), new String[]{"data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8"}, null, null, null);
            query.moveToFirst();
            if (query.getCount() > 0) {
                do {
                    a aVar = new a();
                    aVar.a = query.getString(0);
                    aVar.b = query.getString(1);
                    aVar.c = query.getString(2);
                    aVar.d = query.getString(3);
                    aVar.e = query.getString(4);
                    aVar.f = query.getString(5);
                    aVar.g = query.getString(6);
                    aVar.h = query.getString(7);
                    arrayList.add(aVar);
                    query.move(1);
                } while (!query.isAfterLast());
            }
            query.close();
        } catch (Exception e) {
            h.a(e);
        }
        return arrayList;
    }

    public int b() {
        try {
            return this.b.delete(a("datatb"), null, null);
        } catch (Exception e) {
            h.a(e);
            return 0;
        }
    }
}
