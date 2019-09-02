package cn.jpush.android.a;

import android.support.v4.view.PointerIconCompat;
import android.util.SparseArray;
import cn.jpush.android.d.e;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;

public final class j {
    private static final SparseArray<String> a;

    static {
        SparseArray sparseArray = new SparseArray();
        a = sparseArray;
        sparseArray.put(995, "Message JSON parsing succeed");
        a.put(996, "Message JSON parsing failed");
        a.put(997, "Message already received, give up");
        a.put(998, "Message already received, still process");
        a.put(1000, "User clicked and opened the Message");
        a.put(1001, "Message download succeed");
        a.put(1002, "Message received succeed");
        a.put(PointerIconCompat.TYPE_HELP, "Message silence download succeed");
        a.put(1004, "Video silence downlaod succeed");
        a.put(1005, "User clicked video and jumped to url Message (browser)");
        a.put(PointerIconCompat.TYPE_TEXT, "Video is force closed by user");
        a.put(PointerIconCompat.TYPE_CROSSHAIR, "User clicked 'OK'");
        a.put(PointerIconCompat.TYPE_CELL, "User clicked 'Cancel'");
        a.put(1011, "Download failed");
        a.put(PointerIconCompat.TYPE_NO_DROP, "User clicked to download again");
        a.put(PointerIconCompat.TYPE_ALL_SCROLL, "The file already exist and same size. Don't download again.");
        a.put(1100, "Invalid param or unexpected result.");
        a.put(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, "Failed to preload required resource");
        a.put(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, "User clicked install alert on status bar after downloading finished.");
        a.put(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, "User clicked the webview's url");
        a.put(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, "User clicked call action");
        a.put(PointerIconCompat.TYPE_ZOOM_IN, "The Message show in the status bar");
        a.put(PointerIconCompat.TYPE_ZOOM_OUT, "Click applist and show the Message");
        a.put(PointerIconCompat.TYPE_GRAB, "Down image failed");
        a.put(1021, "Down html failed");
        a.put(1022, "Down Message failed");
        a.put(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC, "Discard the message because it is not in the push time");
        a.put(1031, "Stop push service");
        a.put(1032, "Resume push service");
    }

    public static String a(int i) {
        if (a.get(i) != null) {
            return (String) a.get(i);
        }
        e.c("StatusCode", "Unknown report code - " + i);
        return "";
    }
}
