package com.financial.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(false, 1000, "Invalid Input Value"),

    METHOD_NOT_ALLOWED(false, 1001, "Method Not Allowed"),

    ENTITY_NOT_FOUND(false, 1002, " Entity Not Found"),
    INTERNAL_SERVER_ERROR(false, 1003, "Server Error"),
    INVALID_TYPE_VALUE(false, 1004, " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(false, 1005, "Access is Denied"),
    BAD_REQUEST(false,1006,"Bad Request");

    private final boolean success;
    private final int code;
    private final String message;

    ErrorCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


}
