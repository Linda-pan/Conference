package com.elin4it.ssm.exception;

/**
 *  ajax请求session过期异常
 * Created by jpan on 2016/4/28.
 */
public class AjaxRequestSessionExpireException extends RuntimeException{
    public AjaxRequestSessionExpireException() {
        super();
    }

    public AjaxRequestSessionExpireException(String message) {
        super(message);
    }

    public AjaxRequestSessionExpireException(Throwable cause) {
        super(cause);
    }

    public AjaxRequestSessionExpireException(String message, Throwable cause) {
        super(message, cause);
    }
}
