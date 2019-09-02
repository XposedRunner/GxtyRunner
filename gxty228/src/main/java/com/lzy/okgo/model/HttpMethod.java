package com.lzy.okgo.model;

import com.tencent.connect.common.Constants;

public enum HttpMethod {
    GET(Constants.HTTP_GET),
    POST(Constants.HTTP_POST),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");
    
    private final String value;

    private HttpMethod(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }

    public boolean hasBody() {
        switch (this) {
            case POST:
            case PUT:
            case PATCH:
            case DELETE:
            case OPTIONS:
                return true;
            default:
                return false;
        }
    }
}
