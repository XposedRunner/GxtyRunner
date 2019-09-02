package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class PaintCompat {
    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        if (VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(str);
        }
        return PaintCompatApi14.hasGlyph(paint, str);
    }

    private PaintCompat() {
    }
}
