package com.blankj.utilcode.util;

import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import java.io.File;

public final class UriUtils {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File uri2File(@android.support.annotation.NonNull android.net.Uri r3, java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001f in list [B:10:0x004c]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        if (r3 != 0) goto L_0x000a;
    L_0x0002:
        r0 = new java.lang.NullPointerException;
        r1 = "Argument 'uri' of type Uri (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it";
        r0.<init>(r1);
        throw r0;
    L_0x000a:
        r0 = "file";
        r1 = r3.getScheme();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0020;
    L_0x0016:
        r0 = new java.io.File;
        r1 = r3.getPath();
        r0.<init>(r1);
    L_0x001f:
        return r0;
    L_0x0020:
        r0 = new android.content.CursorLoader;
        r1 = com.blankj.utilcode.util.Utils.getApp();
        r0.<init>(r1);
        r0.setUri(r3);
        r1 = 1;
        r1 = new java.lang.String[r1];
        r2 = 0;
        r1[r2] = r4;
        r0.setProjection(r1);
        r1 = 0;
        r1 = r0.loadInBackground();	 Catch:{ all -> 0x0050 }
        r2 = r1.getColumnIndexOrThrow(r4);	 Catch:{ all -> 0x0050 }
        r1.moveToFirst();	 Catch:{ all -> 0x0050 }
        r0 = new java.io.File;	 Catch:{ all -> 0x0050 }
        r2 = r1.getString(r2);	 Catch:{ all -> 0x0050 }
        r0.<init>(r2);	 Catch:{ all -> 0x0050 }
        if (r1 == 0) goto L_0x001f;
    L_0x004c:
        r1.close();
        goto L_0x001f;
    L_0x0050:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0056;
    L_0x0053:
        r1.close();
    L_0x0056:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.uri2File(android.net.Uri, java.lang.String):java.io.File");
    }

    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Uri file2Uri(@NonNull File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'file' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        } else {
            return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
        }
    }
}
