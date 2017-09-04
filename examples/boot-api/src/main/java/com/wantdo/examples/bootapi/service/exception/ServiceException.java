package com.wantdo.examples.bootapi.service.exception;

public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 2018204296254323205L;

    public ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
