package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class GradeNotFoundException extends RuntimeException
{
    public GradeNotFoundException(String message) {
        super(message);
    }

    public GradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeNotFoundException(Throwable cause) {
        super(cause);
    }
}
