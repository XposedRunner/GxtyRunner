package com.loc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* compiled from: StatisticsPubDataStrategy */
public final class bh extends bi {
    public bh(bi biVar) {
        super(biVar);
    }

    protected final byte[] a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
        stringBuffer.append(" ");
        stringBuffer.append(UUID.randomUUID().toString());
        stringBuffer.append(" ");
        if (stringBuffer.length() != 53) {
            return new byte[0];
        }
        Object a = dl.a(stringBuffer.toString());
        Object obj = new byte[(a.length + bArr.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr, 0, obj, a.length, bArr.length);
        return obj;
    }
}
