package com.financial.global.exception;

public class DuplicateEmailException extends BusinessException{

    public DuplicateEmailException() {
        super("같은 이메일이 존재합니다", ErrorCode.BAD_REQUEST);
    }
}
