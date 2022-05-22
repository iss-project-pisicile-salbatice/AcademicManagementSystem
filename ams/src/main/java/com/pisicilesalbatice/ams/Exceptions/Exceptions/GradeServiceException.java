package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class GradeServiceException extends RuntimeException
{
    public GradeServiceException(String message) {
        super(message);
    }

    public GradeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeServiceException(Throwable cause) {
        super(cause);
    }
}
