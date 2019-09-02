package com.google.gson;

public enum LongSerializationPolicy {
    DEFAULT {
        public k serialize(Long l) {
            return new n((Number) l);
        }
    },
    STRING {
        public k serialize(Long l) {
            return new n(String.valueOf(l));
        }
    };

    public abstract k serialize(Long l);
}
