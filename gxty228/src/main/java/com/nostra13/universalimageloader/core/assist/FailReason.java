package com.nostra13.universalimageloader.core.assist;

public class FailReason {
    private final FailType a;
    private final Throwable b;

    public enum FailType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public FailReason(FailType failType, Throwable th) {
        this.a = failType;
        this.b = th;
    }
}
