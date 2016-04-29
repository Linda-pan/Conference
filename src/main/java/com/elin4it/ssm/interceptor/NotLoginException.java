package com.elin4it.ssm.interceptor;

/**
 * Created by jpan on 2016/4/29.
 */
public class NotLoginException extends Exception {

    private static final long serialVersionUID = 7588379523820777110L;

    private String requestUrl;

    public NotLoginException() {
        super();
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(String requestUrl, String message) {
        super(message);
        this.requestUrl = requestUrl;
    }

    public NotLoginException(String requestUrl, String message, Throwable cause) {
        super(message, cause);
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;

    }
}
