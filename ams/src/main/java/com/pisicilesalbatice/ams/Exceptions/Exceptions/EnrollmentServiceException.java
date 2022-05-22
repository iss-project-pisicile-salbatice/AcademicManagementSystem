package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class EnrollmentServiceException extends RuntimeException
{
    public EnrollmentServiceException(String message) {
        super(message);
    }

    public EnrollmentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnrollmentServiceException(Throwable cause) {
        super(cause);
    }
}
