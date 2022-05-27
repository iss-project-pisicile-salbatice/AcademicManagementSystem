package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class OptionalServiceException extends RuntimeException
{
    public OptionalServiceException(String message) {
        super(message);
    }

    public OptionalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionalServiceException(Throwable cause) {
        super(cause);
    }
}
