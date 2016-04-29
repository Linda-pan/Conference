package com.elin4it.ssm.exception;

/**
 * 业务逻辑异常
 * Created by jpan on 2016/4/29.
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
