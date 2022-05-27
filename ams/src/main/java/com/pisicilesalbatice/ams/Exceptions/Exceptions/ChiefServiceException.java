package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class ChiefServiceException extends RuntimeException
{
    public ChiefServiceException(String message) {
        super(message);
    }

    public ChiefServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChiefServiceException(Throwable cause) {
        super(cause);
    }
}
