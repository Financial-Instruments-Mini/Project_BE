package com.financial.global.exception;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException() {
        super("entity 를 찾을 수 없습니다", ErrorCode.ENTITY_NOT_FOUND);
    }
}
