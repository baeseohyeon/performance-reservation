package com.numble.performancereservation.exception;

public class RLockUnAvailableException extends RuntimeException {

    public RLockUnAvailableException(String message) {
        super(message);
    }
}
