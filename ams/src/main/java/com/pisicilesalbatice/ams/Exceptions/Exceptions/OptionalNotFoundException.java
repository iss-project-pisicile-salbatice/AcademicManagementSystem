package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class OptionalNotFoundException extends RuntimeException
{
    public OptionalNotFoundException(String message) {
        super(message);
    }

    public OptionalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionalNotFoundException(Throwable cause) {
        super(cause);
    }
}
