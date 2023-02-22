package com.financial.global.exception;

public class InvalidTokenException extends BusinessException{
    public InvalidTokenException() {
        super("토큰이 유효하지 않습니다.", ErrorCode.INVALID_TOKEN);
    }
}
