package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class TeacherServiceException extends RuntimeException
{
    public TeacherServiceException(String message) {
        super(message);
    }

    public TeacherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherServiceException(Throwable cause) {
        super(cause);
    }
}
