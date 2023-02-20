package com.financial.global.response;

import com.financial.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private boolean success;
    private int code;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode code, List<FieldError> errors){
        this.success=code.isSuccess();
        this.code=code.getCode();
        this.errors=errors;
        this.message= code.getMessage();
    }

    private ErrorResponse(ErrorCode code){
        this.success=code.isSuccess();
        this.code=code.getCode();
        this.errors=new ArrayList<>();
        this.message= code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code){
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, List<FieldError> errors){
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult){
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e){
        String value = e.getValue() == null ? "" : e.getValue().toString();
        List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }


        public static List<FieldError> of(String field, String value, String reason){
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(BindingResult bindingResult){
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(
                        error -> new FieldError(
                                error.getField(),
                                error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                                error.getDefaultMessage()))
                        .collect(Collectors.toList());
        }

    }


}
