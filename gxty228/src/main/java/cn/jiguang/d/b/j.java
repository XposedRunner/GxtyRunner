package cn.jiguang.d.b;

final class j {
    String a;
    int b;

    public j(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new Exception("Port is needed for a valid address, split by :");
        }
        this.a = str.substring(0, indexOf);
        if (i.c(this.a)) {
            String substring = str.substring(indexOf + 1);
            try {
                this.b = Integer.parseInt(substring);
                if (this.b == 0) {
                    throw new Exception("Invalid port - 0");
                }
                return;
            } catch (Exception e) {
                throw new Exception("Invalid port - " + substring);
            }
        }
        throw new Exception("Invalid ip - " + this.a);
    }

    public final String toString() {
        return this.a + ":" + this.b;
    }
}
