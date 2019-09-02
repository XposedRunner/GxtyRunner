package com.baidu.mobads.openad.d;

import android.os.Build.VERSION;
import com.baidu.mobads.openad.c.c;
import com.baidu.mobads.openad.c.d;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class a extends c {
    public static int a = 1024;
    private static final TimeUnit f = TimeUnit.SECONDS;
    private static int g = 5;
    private static BlockingQueue<Runnable> h = new LinkedBlockingQueue();
    private static ThreadPoolExecutor i;
    private String b;
    private Boolean d;
    private AtomicBoolean e;

    class a implements Runnable {
        final /* synthetic */ a a;
        private b b;
        private double c;

        public a(a aVar, b bVar, double d) {
            this.a = aVar;
            this.b = bVar;
            this.c = d;
        }

        public void run() {
            Throwable th;
            Throwable th2;
            HttpURLConnection httpURLConnection;
            Object obj;
            InputStream inputStream = null;
            try {
                String str = "";
                if (this.b.c > 0) {
                    Thread.sleep(this.b.c);
                }
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.b.a).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout((int) this.c);
                    httpURLConnection2.setUseCaches(false);
                    if (this.b.b != null && this.b.b.length() > 0) {
                        httpURLConnection2.setRequestProperty(HttpHeaders.HEAD_KEY_USER_AGENT, this.b.b);
                    }
                    httpURLConnection2.setRequestProperty("Content-type", this.b.d);
                    httpURLConnection2.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
                    httpURLConnection2.setRequestProperty(HttpHeaders.HEAD_KEY_CACHE_CONTROL, "no-cache");
                    if (Integer.parseInt(VERSION.SDK) < 8) {
                        System.setProperty("http.keepAlive", "false");
                    }
                    if (this.b.e == 1) {
                        httpURLConnection2.setRequestMethod(Constants.HTTP_GET);
                        httpURLConnection2.connect();
                        httpURLConnection2.getHeaderFields();
                        if (this.a.d.booleanValue()) {
                            httpURLConnection2.getResponseCode();
                        } else {
                            inputStream = httpURLConnection2.getInputStream();
                            this.a.dispatchEvent(new d("URLLoader.Load.Complete", a.b(inputStream), this.b.a()));
                        }
                    } else if (this.b.e == 0) {
                        httpURLConnection2.setRequestMethod(Constants.HTTP_POST);
                        httpURLConnection2.setDoInput(true);
                        httpURLConnection2.setDoOutput(true);
                        if (this.b.b() != null) {
                            String encodedQuery = this.b.b().build().getEncodedQuery();
                            OutputStream outputStream = httpURLConnection2.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            bufferedWriter.write(encodedQuery);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            outputStream.close();
                        }
                        httpURLConnection2.connect();
                        httpURLConnection2.getResponseCode();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdURLLoader", th3.getMessage());
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Throwable th4) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdURLLoader", th4.getMessage());
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    httpURLConnection = httpURLConnection2;
                    th4 = th2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th4;
                }
            } catch (Throwable th6) {
                th4 = th6;
                obj = inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th4;
            }
        }
    }

    static {
        try {
            i = new ThreadPoolExecutor(g, g, 1, f, h);
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e(e);
        }
    }

    public a(String str) {
        this.d = Boolean.valueOf(false);
        this.e = new AtomicBoolean();
        this.b = str;
    }

    public a() {
        this(null);
    }

    public void a(b bVar, Boolean bool) {
        this.d = bool;
        a(bVar, 20000.0d);
    }

    public void a(b bVar) {
        a(bVar, 20000.0d);
    }

    public void a(b bVar, double d) {
        try {
            i.execute(new a(this, bVar, d));
        } catch (Exception e) {
        }
    }

    private static String b(InputStream inputStream) {
        String str = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str2 = "";
        while (true) {
            str2 = bufferedReader.readLine();
            if (str2 == null) {
                return str;
            }
            str = str + str2 + "\n";
        }
    }

    public void a() {
    }

    public void dispose() {
        this.e.set(true);
        a();
        super.dispose();
    }
}
