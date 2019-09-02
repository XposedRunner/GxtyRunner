package cn.jiguang.d.d;

import java.io.File;
import java.io.FileFilter;

final class n implements FileFilter {
    n() {
    }

    public final boolean accept(File file) {
        return file.getAbsolutePath().contains(File.separator + "nowrap");
    }
}
