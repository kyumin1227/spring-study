package dev.be.sns.exception;

public class SnsApplicationException extends RuntimeException {
    public SnsApplicationException(String message) {
        super(message);
    }
}
