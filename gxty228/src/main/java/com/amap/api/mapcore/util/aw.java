package com.amap.api.mapcore.util;

@hf(a = "update_item_download_info")
/* compiled from: DTDownloadInfo */
class aw {
    @hg(a = "mAdcode", b = 6)
    private String a = "";
    @hg(a = "fileLength", b = 5)
    private long b = 0;
    @hg(a = "splitter", b = 2)
    private int c = 0;
    @hg(a = "startPos", b = 5)
    private long d = 0;
    @hg(a = "endPos", b = 5)
    private long e = 0;

    public aw(String str, long j, int i, long j2, long j3) {
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = j2;
        this.e = j3;
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
