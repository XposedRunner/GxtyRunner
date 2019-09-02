package com.baidu.mobads.production;

import com.baidu.mobads.openad.c.d;
import com.baidu.mobads.openad.d.a;
import com.baidu.mobads.openad.d.b;

public class v extends a {
    public void a(b bVar, String str) {
        dispatchEvent(new d("URLLoader.Load.Complete", str, bVar.a()));
    }
}
