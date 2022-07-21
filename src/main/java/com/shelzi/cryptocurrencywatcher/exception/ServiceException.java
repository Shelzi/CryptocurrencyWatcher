package com.shelzi.cryptocurrencywatcher.exception;

public class ServiceException extends RuntimeException {
    private String detail;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}