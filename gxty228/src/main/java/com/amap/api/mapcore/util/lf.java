package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLES20;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.autonavi.amap.mapcore.interfaces.IglModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: Gl3dModelManager */
public class lf {
    private long a = 0;
    private Context b;
    private lj c;
    private List<cd> d = new ArrayList();
    private List<Integer> e = new ArrayList();

    public lf(Context context, lj ljVar) {
        this.b = context;
        this.c = ljVar;
    }

    public GL3DModel a(GL3DModelOptions gL3DModelOptions) {
        if (gL3DModelOptions == null) {
            return null;
        }
        GL3DModel gL3DModel;
        IglModel cdVar = new cd(this, gL3DModelOptions, this.c);
        StringBuilder append = new StringBuilder().append("model_");
        long j = this.a;
        this.a = 1 + j;
        cdVar.a(append.append(j).toString());
        synchronized (this.d) {
            this.d.add(cdVar);
            gL3DModel = new GL3DModel(cdVar);
        }
        return gL3DModel;
    }

    public void a() {
        for (cd cdVar : this.d) {
            if (cdVar.isVisible()) {
                cdVar.a();
            }
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.clear();
        }
    }

    public void c() {
        if (this.d != null) {
            for (cd destroy : this.d) {
                destroy.destroy();
            }
            this.d.clear();
        }
    }

    public void d() {
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                GLES20.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            }
        }
    }

    public void a(String str) {
        try {
            if (this.d != null && this.d.size() > 0) {
                cd cdVar = null;
                for (int i = 0; i < this.d.size(); i++) {
                    cdVar = (cd) this.d.get(i);
                    if (str.equals(cdVar.getId())) {
                        break;
                    }
                }
                if (cdVar != null) {
                    this.d.remove(cdVar);
                    cdVar.destroy();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i) {
        this.e.add(Integer.valueOf(i));
    }
}
