package com.autonavi.amap.mapcore.maploader;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.mapcore.util.fx;
import com.amap.api.mapcore.util.gb;
import com.amap.api.mapcore.util.gd;
import com.amap.api.mapcore.util.gz;
import com.amap.api.mapcore.util.ix;
import com.autonavi.ae.gmap.GLMapEngine;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

public class AMapLoader {
    private static final String AMAP_HOST_KEY = "targetHost";
    private static final int CIFA_UPLOAD_TIMELIMIT = 60000;
    private static final int CONNECTION_TIMEOUT = 20000;
    private static final int GET_METHOD = 0;
    private static String mDiu;
    private static long mLastRecordTime = 0;
    private volatile boolean isCanceled = false;
    public boolean isFinish = false;
    ADataRequestParam mDataRequestParam;
    private int mEngineID = 0;
    GLMapEngine mGLMapEngine;
    private boolean mRequestCancel = false;
    private HttpURLConnection mURLConnection = null;
    private String oriHost = "";

    public static class ADataRequestParam {
        public byte[] enCodeString;
        public long handler;
        public int nCompress;
        public int nRequestType;
        public String requestBaseUrl;
        public String requestUrl;
    }

    public AMapLoader(int i, GLMapEngine gLMapEngine, ADataRequestParam aDataRequestParam) {
        this.mDataRequestParam = aDataRequestParam;
        this.mEngineID = i;
        this.mGLMapEngine = gLMapEngine;
        this.mRequestCancel = false;
    }

    public void doRequest() {
        Throwable th;
        InputStream inputStream = null;
        Object obj = 1;
        if (!this.mRequestCancel) {
            String str;
            boolean z;
            String str2 = this.mDataRequestParam.requestBaseUrl;
            String str3 = this.mDataRequestParam.requestUrl;
            str2 = generateUrl(str2);
            if (str2.endsWith(HttpUtils.URL_AND_PARA_SEPARATOR)) {
                str = str2;
            } else {
                str = str2 + HttpUtils.URL_AND_PARA_SEPARATOR;
            }
            str2 = str3.replaceAll(";", getEncodeRequestParams(";").toString());
            if (str == null || !str.contains("http://m5.amap.com/")) {
                z = false;
            } else {
                z = true;
            }
            str2 = getRequestParams(str2, z, this.mDataRequestParam.nRequestType);
            StringBuffer stringBuffer = new StringBuffer();
            if (this.mDataRequestParam.nRequestType == 0) {
                stringBuffer.append(str2);
                stringBuffer.append("&csid=" + UUID.randomUUID().toString());
            } else {
                stringBuffer.append("csid=" + UUID.randomUUID().toString());
            }
            InputStream inputStream2 = null;
            try {
                InputStream inputStream3;
                URL url = new URL(str + generateQueryString(this.mGLMapEngine.getContext(), stringBuffer.toString()));
                if (url != null) {
                    if (null != null) {
                        this.mURLConnection = (HttpURLConnection) url.openConnection(null);
                    }
                    if (this.mURLConnection == null) {
                        this.mURLConnection = (HttpURLConnection) url.openConnection();
                    }
                    this.mURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                    this.mURLConnection.setReadTimeout(CONNECTION_TIMEOUT);
                    generateAMapHeader(this.mURLConnection);
                    if (this.mDataRequestParam.nRequestType == 0) {
                        this.mURLConnection.setRequestMethod(Constants.HTTP_GET);
                    } else {
                        this.mURLConnection.setRequestMethod(Constants.HTTP_POST);
                        this.mURLConnection.setDoInput(true);
                        this.mURLConnection.setDoOutput(true);
                        this.mURLConnection.setUseCaches(false);
                        this.mURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_USER_AGENT, this.mGLMapEngine.getUserAgent());
                        byte[] bytes = str2.getBytes("UTF-8");
                        OutputStream outputStream = this.mURLConnection.getOutputStream();
                        outputStream.write(bytes);
                        outputStream.flush();
                        outputStream.close();
                    }
                    this.mURLConnection.connect();
                    if (this.mRequestCancel) {
                        doCancel();
                        if (null != null) {
                            try {
                                inputStream2.close();
                                return;
                            } catch (IOException e) {
                                return;
                            } catch (ArrayIndexOutOfBoundsException e2) {
                                return;
                            } catch (NullPointerException e3) {
                                return;
                            }
                        }
                        return;
                    }
                    int responseCode = this.mURLConnection.getResponseCode();
                    if (this.mRequestCancel) {
                        doCancel();
                        if (null != null) {
                            try {
                                inputStream2.close();
                                return;
                            } catch (IOException e4) {
                                return;
                            } catch (ArrayIndexOutOfBoundsException e5) {
                                return;
                            } catch (NullPointerException e6) {
                                return;
                            }
                        }
                        return;
                    } else if (responseCode == 200) {
                        InputStream inputStream4 = this.mURLConnection.getInputStream();
                        try {
                            Object obj2;
                            byte[] bArr = new byte[512];
                            if (this.mRequestCancel) {
                                obj2 = 1;
                            } else {
                                obj2 = null;
                            }
                            while (obj2 == null) {
                                int read = inputStream4.read(bArr);
                                if (read <= -1) {
                                    break;
                                }
                                this.mGLMapEngine.receiveNetData(this.mEngineID, this.mDataRequestParam.handler, bArr, read);
                                if (this.mRequestCancel) {
                                    break;
                                }
                            }
                            obj = obj2;
                            if (obj == null) {
                                this.mGLMapEngine.finishDownLoad(this.mEngineID, this.mDataRequestParam.handler);
                            }
                            inputStream3 = inputStream4;
                            doCancel();
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e7) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e8) {
                                    return;
                                } catch (NullPointerException e9) {
                                    return;
                                }
                            }
                        } catch (OutOfMemoryError e10) {
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e11) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e12) {
                                    return;
                                } catch (NullPointerException e13) {
                                    return;
                                }
                            }
                        } catch (IllegalStateException e14) {
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e15) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e16) {
                                    return;
                                } catch (NullPointerException e17) {
                                    return;
                                }
                            }
                        } catch (IndexOutOfBoundsException e18) {
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e19) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e20) {
                                    return;
                                } catch (NullPointerException e21) {
                                    return;
                                }
                            }
                        } catch (SocketTimeoutException e22) {
                            inputStream = inputStream4;
                            try {
                                this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                                doCancel();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e23) {
                                        return;
                                    } catch (ArrayIndexOutOfBoundsException e24) {
                                        return;
                                    } catch (NullPointerException e25) {
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                doCancel();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e26) {
                                    } catch (ArrayIndexOutOfBoundsException e27) {
                                    } catch (NullPointerException e28) {
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException e29) {
                            inputStream = inputStream4;
                            this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e30) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e31) {
                                    return;
                                } catch (NullPointerException e32) {
                                    return;
                                }
                            }
                        } catch (NullPointerException e33) {
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e34) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e35) {
                                    return;
                                } catch (NullPointerException e36) {
                                    return;
                                }
                            }
                        } catch (NumberFormatException e37) {
                            inputStream = inputStream4;
                            this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e38) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e39) {
                                    return;
                                } catch (NullPointerException e40) {
                                    return;
                                }
                            }
                        } catch (Exception e41) {
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e42) {
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e43) {
                                    return;
                                } catch (NullPointerException e44) {
                                    return;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = inputStream4;
                            doCancel();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } else {
                        this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, responseCode);
                    }
                }
                inputStream3 = null;
                doCancel();
                if (inputStream3 != null) {
                    inputStream3.close();
                }
            } catch (OutOfMemoryError e45) {
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IllegalStateException e46) {
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IndexOutOfBoundsException e47) {
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (SocketTimeoutException e48) {
                this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e49) {
                this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (NullPointerException e50) {
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (NumberFormatException e51) {
                this.mGLMapEngine.netError(this.mEngineID, this.mDataRequestParam.handler, -1);
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e52) {
                doCancel();
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
    }

    private void generateAMapHeader(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null && !TextUtils.isEmpty(ix.b) && !TextUtils.isEmpty(this.oriHost)) {
            httpURLConnection.setRequestProperty(AMAP_HOST_KEY, this.oriHost);
        }
    }

    private String generateUrl(String str) {
        if (TextUtils.isEmpty(ix.b)) {
            return str;
        }
        Uri parse = Uri.parse(str);
        this.oriHost = parse.getHost();
        return parse.buildUpon().encodedAuthority(ix.b).build().toString();
    }

    public void doCancel() {
        this.mRequestCancel = true;
        if (this.mURLConnection != null && !this.isCanceled) {
            synchronized (this.mURLConnection) {
                try {
                    this.isCanceled = true;
                    this.mURLConnection.disconnect();
                    this.mGLMapEngine.setMapLoaderToTask(this.mEngineID, this.mDataRequestParam.handler, null);
                } catch (Exception e) {
                }
            }
        }
    }

    private String getEncodeRequestParams(String str) {
        String str2 = null;
        try {
            str2 = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public String getDeviceId(Context context) {
        if (context != null) {
            return gd.u(context);
        }
        return null;
    }

    protected String getRequestParams(String str, boolean z, int i) {
        if (mDiu == null) {
            mDiu = getDeviceId(this.mGLMapEngine.getContext());
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        if (z) {
            stringBuffer.append("&channel=amap7&div=GNaviMap");
            stringBuffer.append("&diu=").append(mDiu);
        } else {
            stringBuffer.append("&channel=amapapi");
            stringBuffer.append("&div=GNaviMap");
            stringBuffer.append("&diu=").append(mDiu);
        }
        return stringBuffer.toString();
    }

    private String generateQueryString(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append("&key=").append(fx.f(this.mGLMapEngine.getContext()));
        String sortReEncoderParams = sortReEncoderParams(stringBuffer.toString());
        String a = gb.a();
        stringBuffer.append("&ts=" + a);
        stringBuffer.append("&scode=" + gb.a(context, a, sortReEncoderParams));
        return stringBuffer.toString();
    }

    private String sortReEncoderParams(String str) {
        String[] split = str.split(HttpUtils.PARAMETERS_SEPARATOR);
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String strReEncoder : split) {
            stringBuffer.append(strReEncoder(strReEncoder));
            stringBuffer.append(HttpUtils.PARAMETERS_SEPARATOR);
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    private String strReEncoder(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Throwable e) {
            gz.c(e, "AbstractProtocalHandler", "strReEncoder");
            return "";
        } catch (Throwable e2) {
            gz.c(e2, "AbstractProtocalHandler", "strReEncoderException");
            return "";
        }
    }
}
