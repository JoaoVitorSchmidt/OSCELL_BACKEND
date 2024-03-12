package com.oscell.oscell.commons.response;

public final class ServiceOrderResponse<T> {
    private Status statusCode;
    private T content;
    private String message;
    private boolean error;

    public ServiceOrderResponse(final Status statusCode, final T content, final boolean error) {
        this.statusCode = statusCode;
        this.content = content;
        this.error = error;
    }

    public ServiceOrderResponse(final Status statusCode, final T content, final String message, final boolean error) {
        this.statusCode = statusCode;
        this.content = content;
        this.error = error;
        this.message = message;
    }

    public ServiceOrderResponse(final Status statusCode, final String message, final boolean error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }

    public static <T> ServiceOrderResponse<T> ok(final T content) {
        return new ServiceOrderResponse<T>(Status.OK, content, false);
    }

    public static <T> ServiceOrderResponse<T> error(final String message) {
        return new ServiceOrderResponse<T>(Status.ERROR, message , true);
    }

    public static <T> ServiceOrderResponse<T> errorWithContent(final T content, final String message) {
        return new ServiceOrderResponse<T>(Status.ERROR, content, message, true);
    }

    
    public Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Status statusCode) {
        this.statusCode = statusCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Status {
        ERROR,
        OK
    }
}
