package io.test.sas.common.exception;

import lombok.Getter;

@Getter
public class AppException extends Exception {

    private String errorCode;

    private Object[] params;

    public AppException(String errorCode, Object... params) {
        super(errorCode.toString());

        this.errorCode = errorCode;
        this.params = params;
    }
}