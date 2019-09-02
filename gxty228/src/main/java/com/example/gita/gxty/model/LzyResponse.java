package com.example.gita.gxty.model;

import java.io.Serializable;

public class LzyResponse<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;
    public int code;
    public T data;
    public String msg;
    public String newMedal;
}
