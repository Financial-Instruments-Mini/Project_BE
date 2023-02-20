package com.financial.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseResponse<T> {
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.success = true;
        this.code = 200;
        this.message = "요청에 성공하였습니다.";
        this.data = data;
    }

    public static <T> BaseResponse<T> of(T data){
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> empty(){
        return new BaseResponse<>(null);
    }

}
