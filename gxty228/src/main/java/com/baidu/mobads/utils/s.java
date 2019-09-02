package com.baidu.mobads.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build.VERSION;
import cn.jiguang.net.HttpUtils;
import com.baidu.mobads.AdSettings;
import com.baidu.mobads.a.b;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.d.a;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class s implements IXAdURIUitls {
    public HashMap<String, String> getAllQueryParameters(String str) {
        HashMap<String, String> hashMap = new HashMap();
        Uri parse = Uri.parse(str);
        for (String str2 : getQueryParameterNames(parse)) {
            if (str2 != null && str2.length() > 0) {
                hashMap.put(str2, parse.getQueryParameter(str2));
            }
        }
        return hashMap;
    }

    @SuppressLint({"NewApi"})
    public Set<String> getQueryParameterNames(Uri uri) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return uri.getQueryParameterNames();
            }
            String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                return Collections.emptySet();
            }
            Set linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (true) {
                int indexOf = encodedQuery.indexOf(38, i);
                if (indexOf == -1) {
                    indexOf = encodedQuery.length();
                }
                int indexOf2 = encodedQuery.indexOf(61, i);
                if (indexOf2 > indexOf || indexOf2 == -1) {
                    indexOf2 = indexOf;
                }
                linkedHashSet.add(Uri.decode(encodedQuery.substring(i, indexOf2)));
                indexOf2 = indexOf + 1;
                if (indexOf2 >= encodedQuery.length()) {
                    return Collections.unmodifiableSet(linkedHashSet);
                }
                i = indexOf2;
            }
        } catch (Exception e) {
            return new HashSet();
        }
    }

    public String getRequestAdUrl(String str, HashMap<String, String> hashMap) {
        String str2;
        String str3;
        XAdSDKFoundationFacade instance = XAdSDKFoundationFacade.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        if (hashMap != null) {
            int i = 0;
            for (String str32 : hashMap.keySet()) {
                int i2 = i + 1;
                str2 = (String) hashMap.get(str32);
                if (i2 == 1) {
                    stringBuilder.append(str32).append(HttpUtils.EQUAL_SIGN).append(str2);
                } else {
                    stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR).append(str32).append(HttpUtils.EQUAL_SIGN).append(str2);
                }
                i = i2;
            }
        }
        if (b.a.booleanValue()) {
            if (hashMap != null) {
                for (String str322 : hashMap.keySet()) {
                    try {
                        str2 = (String) hashMap.get(str322);
                        if (str2 != null) {
                            hashMap.put(str322, URLEncoder.encode(str2, "UTF-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            str322 = instance.getURIUitls().addParameters(str, hashMap) + "&b" + System.currentTimeMillis() + "=1";
        } else {
            str322 = str + "?code2=" + instance.getBase64().encode(stringBuilder.toString() + "&b" + System.currentTimeMillis() + "=1");
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("&b" + System.currentTimeMillis()).append(HttpUtils.EQUAL_SIGN).append("1");
        return str322 + stringBuilder2.toString();
    }

    @Deprecated
    public String addParameter(String str, String str2, String str3) {
        String fixedString = getFixedString(str);
        String queryString = getQueryString(str);
        if (XAdSDKFoundationFacade.getInstance().getCommonUtils().isStringAvailable(queryString)) {
            queryString = queryString + HttpUtils.PARAMETERS_SEPARATOR + str2 + HttpUtils.EQUAL_SIGN + str3;
        } else {
            queryString = str2 + HttpUtils.EQUAL_SIGN + str3;
        }
        return fixedString + HttpUtils.URL_AND_PARA_SEPARATOR + queryString;
    }

    public String addParameters(String str, HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (hashMap == null || hashMap.isEmpty()) {
            return stringBuilder.toString();
        }
        stringBuilder.append(HttpUtils.URL_AND_PARA_SEPARATOR);
        for (Entry entry : hashMap.entrySet()) {
            try {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append(HttpUtils.EQUAL_SIGN);
                stringBuilder.append((String) entry.getValue());
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            } catch (Throwable e) {
                l.a().e(e);
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        return stringBuilder2.substring(0, stringBuilder2.length() - 1);
    }

    public String encodeUrl(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Boolean isHttpProtocol(String str) {
        return a(str, "http:");
    }

    public Boolean isHttpsProtocol(String str) {
        return a(str, "https:");
    }

    public String replaceURLWithSupportProtocol(String str) {
        if (AdSettings.a().equals(AdSettings.b.HTTPS_PROTOCOL_TYPE.a()) && isHttpProtocol(str).booleanValue()) {
            return str.replaceFirst("(?i)http", "https");
        }
        return str;
    }

    public Boolean a(String str) {
        boolean z = a(str, "sms:").booleanValue() || a(str, "smsto:").booleanValue() || a(str, "mms:").booleanValue();
        return Boolean.valueOf(z);
    }

    private Boolean a(String str, String str2) {
        boolean z = false;
        if (str != null && str.trim().toLowerCase(Locale.getDefault()).indexOf(str2) == 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public String getFileName(String str) {
        try {
            String path = new URI(str).getPath();
            return path.substring(path.lastIndexOf(47) + 1, path.length());
        } catch (URISyntaxException e) {
            return "";
        }
    }

    public HttpURLConnection getHttpURLConnection(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    public String getFixedString(String str) {
        if (str == null) {
            return null;
        }
        return (isHttpProtocol(str).booleanValue() || isHttpsProtocol(str).booleanValue()) ? str.split("\\?")[0] : str;
    }

    public String getQueryString(String str) {
        if (str == null) {
            return null;
        }
        String[] split;
        if (isHttpProtocol(str).booleanValue() || isHttpsProtocol(str).booleanValue()) {
            split = str.split("\\?");
        } else {
            split = null;
        }
        if (split == null || split.length < 2) {
            return null;
        }
        return split[1];
    }

    public void pintHttpInNewThread(String str) {
        a aVar = new a();
        com.baidu.mobads.openad.d.b bVar = new com.baidu.mobads.openad.d.b(str, "");
        bVar.e = 1;
        aVar.a(bVar, Boolean.valueOf(true));
    }
}
