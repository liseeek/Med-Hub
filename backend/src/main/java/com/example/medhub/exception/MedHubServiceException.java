package com.example.medhub.exception;

public class MedHubServiceException extends RuntimeException {

    public MedHubServiceException(String message) {
        super(message);
    }

    public MedHubServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
