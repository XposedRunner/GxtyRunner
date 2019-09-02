package cn.jpush.android.b;

import cn.jiguang.net.HttpRequest;
import cn.jiguang.net.HttpResponse;
import cn.jiguang.net.HttpUtils;
import cn.jpush.android.d.e;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class a {
    public static HttpResponse a(String str, int i, long j) {
        HttpResponse httpGet;
        int responseCode;
        Throwable th;
        AssertionError assertionError;
        e.c("HttpManager", "action:httpSimpleGet - " + str);
        if (j < 200 || j > 60000) {
            j = 2000;
        }
        int i2 = 0;
        HttpResponse httpResponse = null;
        while (true) {
            try {
                HttpRequest httpRequest = new HttpRequest(str);
                httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "Close");
                httpRequest.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
                httpGet = HttpUtils.httpGet(null, httpRequest);
                try {
                    responseCode = httpGet.getResponseCode();
                    e.a("HttpManager", "statusCode:" + responseCode);
                    if (responseCode == 200) {
                        break;
                    }
                } catch (Throwable e) {
                    Throwable th2 = e;
                    httpResponse = httpGet;
                    th = th2;
                    e.a("HttpManager", "http client execute error", th);
                    httpGet = httpResponse;
                    if (i2 >= 5) {
                        return httpGet;
                    }
                    responseCode = i2 + 1;
                    try {
                        Thread.sleep(j);
                        i2 = responseCode;
                        httpResponse = httpGet;
                    } catch (InterruptedException e2) {
                        i2 = responseCode;
                        httpResponse = httpGet;
                    }
                } catch (AssertionError e3) {
                    AssertionError assertionError2 = e3;
                    httpResponse = httpGet;
                    assertionError = assertionError2;
                    e.i("HttpManager", "Catch AssertionError to avoid http close crash - " + assertionError.toString());
                    httpGet = httpResponse;
                    if (i2 >= 5) {
                        return httpGet;
                    }
                    responseCode = i2 + 1;
                    Thread.sleep(j);
                    i2 = responseCode;
                    httpResponse = httpGet;
                }
            } catch (Exception e4) {
                th = e4;
                e.a("HttpManager", "http client execute error", th);
                httpGet = httpResponse;
                if (i2 >= 5) {
                    return httpGet;
                }
                responseCode = i2 + 1;
                Thread.sleep(j);
                i2 = responseCode;
                httpResponse = httpGet;
            } catch (AssertionError e5) {
                assertionError = e5;
                e.i("HttpManager", "Catch AssertionError to avoid http close crash - " + assertionError.toString());
                httpGet = httpResponse;
                if (i2 >= 5) {
                    return httpGet;
                }
                responseCode = i2 + 1;
                Thread.sleep(j);
                i2 = responseCode;
                httpResponse = httpGet;
            }
            if (i2 >= 5) {
                break;
            }
            responseCode = i2 + 1;
            Thread.sleep(j);
            i2 = responseCode;
            httpResponse = httpGet;
        }
        return httpGet;
    }

    public static byte[] a(String str, int i, long j, int i2) {
        byte[] bArr = null;
        for (int i3 = 0; i3 < 4; i3++) {
            bArr = b(str, 5, 5000);
            if (bArr != null) {
                break;
            }
        }
        return bArr;
    }

    private static byte[] b(String str, int i, long j) {
        HttpURLConnection httpURLConnection;
        int i2;
        InputStream inputStream;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        byte[] readInputStream;
        byte[] bArr;
        int i3 = 0;
        if (i <= 0 || i > 10) {
            i = 1;
        }
        if (j < 200 || j > 60000) {
            j = 2000;
        }
        e.c("HttpManager", "action:httpGet - " + str);
        int i4 = -1;
        int i5 = 0;
        InputStream inputStream2 = null;
        HttpURLConnection httpURLConnection3 = null;
        while (true) {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
                    httpURLConnection.addRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "Close");
                    i4 = httpURLConnection.getResponseCode();
                    e.a("HttpManager", "statusCode:" + i4);
                    if (i4 == 200) {
                        break;
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        httpURLConnection3 = httpURLConnection;
                    } else {
                        httpURLConnection3 = httpURLConnection;
                    }
                    if (i5 >= i) {
                        return null;
                    }
                    i2 = i5 + 1;
                    try {
                        Thread.sleep(((long) i2) * j);
                        i5 = i2;
                    } catch (InterruptedException e2) {
                        i5 = i2;
                    }
                } catch (SSLPeerUnverifiedException e3) {
                    int i6 = i3;
                    i3 = i4;
                    inputStream = inputStream2;
                    httpURLConnection2 = httpURLConnection;
                    i2 = i6;
                    try {
                        e.j("HttpManager", "Catch SSLPeerUnverifiedException, http client execute error!");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (httpURLConnection2 == null) {
                            httpURLConnection3 = httpURLConnection2;
                            inputStream2 = inputStream;
                            i4 = i3;
                            i3 = i2;
                        } else {
                            httpURLConnection2.disconnect();
                            httpURLConnection3 = httpURLConnection2;
                            inputStream2 = inputStream;
                            i4 = i3;
                            i3 = i2;
                        }
                        if (i5 >= i) {
                            return null;
                        }
                        i2 = i5 + 1;
                        Thread.sleep(((long) i2) * j);
                        i5 = i2;
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection3 = httpURLConnection2;
                        inputStream2 = inputStream;
                    }
                } catch (SSLHandshakeException e5) {
                } catch (Throwable e6) {
                    Throwable th3 = e6;
                    httpURLConnection3 = httpURLConnection;
                    th = th3;
                    try {
                        e.a("HttpManager", "http client execute error", th);
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (i5 >= i) {
                            return null;
                        }
                        i2 = i5 + 1;
                        Thread.sleep(((long) i2) * j);
                        i5 = i2;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } catch (SSLPeerUnverifiedException e8) {
                i2 = i3;
                i3 = i4;
                inputStream = inputStream2;
                httpURLConnection2 = httpURLConnection3;
                e.j("HttpManager", "Catch SSLPeerUnverifiedException, http client execute error!");
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection2 == null) {
                    httpURLConnection2.disconnect();
                    httpURLConnection3 = httpURLConnection2;
                    inputStream2 = inputStream;
                    i4 = i3;
                    i3 = i2;
                } else {
                    httpURLConnection3 = httpURLConnection2;
                    inputStream2 = inputStream;
                    i4 = i3;
                    i3 = i2;
                }
                if (i5 >= i) {
                    return null;
                }
                i2 = i5 + 1;
                Thread.sleep(((long) i2) * j);
                i5 = i2;
            } catch (SSLHandshakeException e9) {
                httpURLConnection = httpURLConnection3;
            } catch (Exception e10) {
                th = e10;
                e.a("HttpManager", "http client execute error", th);
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                if (i5 >= i) {
                    return null;
                }
                i2 = i5 + 1;
                Thread.sleep(((long) i2) * j);
                i5 = i2;
            }
        }
        i3 = httpURLConnection.getContentLength();
        inputStream2 = httpURLConnection.getInputStream();
        if (inputStream2 != null) {
            readInputStream = HttpUtils.readInputStream(inputStream2);
        } else {
            readInputStream = null;
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e11) {
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            bArr = readInputStream;
        } else {
            bArr = readInputStream;
        }
        if (200 == i4) {
            if (i3 != 0) {
                try {
                    e.c("HttpManager", "Unexpected: downloaded bytes content length is 0");
                    return null;
                } catch (Throwable th5) {
                    e.a("HttpManager", "parse response error", th5);
                    return null;
                }
            } else if (bArr.length < i3) {
                return bArr;
            } else {
                e.c("HttpManager", "Download bytes failed. Got bytes len < header content length.");
                return null;
            }
        } else if (400 != i4) {
            e.c("HttpManager", "server response failure - " + str);
            return null;
        } else if (404 == i4) {
            e.c("HttpManager", "Request path does not exist: 404 - " + str);
            return null;
        } else {
            e.c("HttpManager", "Other wrong response status - " + i4 + ", url:" + str);
            return null;
        }
        if (httpURLConnection == null) {
            httpURLConnection.disconnect();
            bArr = null;
        } else {
            bArr = null;
        }
        if (200 == i4) {
            if (400 != i4) {
                e.c("HttpManager", "server response failure - " + str);
                return null;
            } else if (404 == i4) {
                e.c("HttpManager", "Other wrong response status - " + i4 + ", url:" + str);
                return null;
            } else {
                e.c("HttpManager", "Request path does not exist: 404 - " + str);
                return null;
            }
        } else if (i3 != 0) {
            e.c("HttpManager", "Unexpected: downloaded bytes content length is 0");
            return null;
        } else if (bArr.length < i3) {
            return bArr;
        } else {
            e.c("HttpManager", "Download bytes failed. Got bytes len < header content length.");
            return null;
        }
        try {
            e.d("HttpManager", "Catch SSLHandshakeException, http client execute error!");
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e12) {
                }
            }
            if (httpURLConnection == null) {
                bArr = null;
            } else {
                httpURLConnection.disconnect();
                bArr = null;
            }
            if (200 == i4) {
                if (i3 != 0) {
                    e.c("HttpManager", "Unexpected: downloaded bytes content length is 0");
                    return null;
                } else if (bArr.length < i3) {
                    return bArr;
                } else {
                    e.c("HttpManager", "Download bytes failed. Got bytes len < header content length.");
                    return null;
                }
            } else if (400 != i4) {
                e.c("HttpManager", "server response failure - " + str);
                return null;
            } else if (404 == i4) {
                e.c("HttpManager", "Request path does not exist: 404 - " + str);
                return null;
            } else {
                e.c("HttpManager", "Other wrong response status - " + i4 + ", url:" + str);
                return null;
            }
        } catch (Throwable th6) {
            httpURLConnection3 = httpURLConnection;
            th5 = th6;
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e13) {
                }
            }
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            throw th5;
        }
    }
}
