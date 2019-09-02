package cn.jiguang.d.g;

import java.io.File;
import java.util.Comparator;

final class d implements Comparator<File> {
    d() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return (int) (((File) obj).lastModified() - ((File) obj2).lastModified());
    }
}
