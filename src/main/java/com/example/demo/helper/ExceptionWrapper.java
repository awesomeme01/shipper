package com.example.demo.helper;

public class ExceptionWrapper {
    private String errorMessage;
    private Object errorCause;
    private Object errorStackTrace;

    public ExceptionWrapper(Exception exception) {
        this.errorMessage = exception.getMessage();
        this.errorCause = exception.getCause();
        this.errorStackTrace = exception.getStackTrace();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(Object errorCause) {
        this.errorCause = errorCause;
    }

    public Object getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(Object errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }
}
