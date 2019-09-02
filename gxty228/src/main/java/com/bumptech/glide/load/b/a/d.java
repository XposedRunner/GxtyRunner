package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.p;

/* compiled from: FileDescriptorStringLoader */
public class d extends p<ParcelFileDescriptor> implements b<String> {

    /* compiled from: FileDescriptorStringLoader */
    public static class a implements m<String, ParcelFileDescriptor> {
        public l<String, ParcelFileDescriptor> a(Context context, c cVar) {
            return new d(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }

        public void a() {
        }
    }

    public d(l<Uri, ParcelFileDescriptor> lVar) {
        super(lVar);
    }
}
