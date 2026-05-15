package com.seveneleven.employeeapi.exception;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
