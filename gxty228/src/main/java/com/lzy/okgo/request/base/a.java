package com.lzy.okgo.request.base;

import com.lzy.okgo.f.d;
import com.lzy.okgo.model.Progress;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* compiled from: ProgressRequestBody */
public class a<T> extends RequestBody {
    private RequestBody a;
    private com.lzy.okgo.b.b<T> b;
    private b c;

    /* compiled from: ProgressRequestBody */
    private final class a extends ForwardingSink {
        final /* synthetic */ a a;
        private Progress b = new Progress();

        a(a aVar, Sink sink) {
            this.a = aVar;
            super(sink);
            this.b.totalSize = aVar.contentLength();
        }

        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            Progress.changeProgress(this.b, j, new com.lzy.okgo.model.Progress.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(Progress progress) {
                    if (this.a.a.c != null) {
                        this.a.a.c.a(progress);
                    } else {
                        this.a.a.a(progress);
                    }
                }
            });
        }
    }

    /* compiled from: ProgressRequestBody */
    public interface b {
        void a(Progress progress);
    }

    a(RequestBody requestBody, com.lzy.okgo.b.b<T> bVar) {
        this.a = requestBody;
        this.b = bVar;
    }

    public MediaType contentType() {
        return this.a.contentType();
    }

    public long contentLength() {
        try {
            return this.a.contentLength();
        } catch (Throwable e) {
            d.a(e);
            return -1;
        }
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(new a(this, bufferedSink));
        this.a.writeTo(buffer);
        buffer.flush();
    }

    private void a(final Progress progress) {
        com.lzy.okgo.f.b.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                if (this.b.b != null) {
                    this.b.b.a(progress);
                }
            }
        });
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
