package com.alansf.apibook.apibook.enums;

public enum ReadStatus {

    WANT(1),
    READING(2),
    READ(3);

    private final int code;

    ReadStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ReadStatus valueOf(int code) {
        for(ReadStatus value : ReadStatus.values()) {
            if(code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ReadStatus code!");
    }
}
