package com.amigoscode.testing.exception;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String errorMessage, Throwable exception) {
        super(errorMessage, exception);
    }
}
