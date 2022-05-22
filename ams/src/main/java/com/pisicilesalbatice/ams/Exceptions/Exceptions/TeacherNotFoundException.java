package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class TeacherNotFoundException extends RuntimeException
{
    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherNotFoundException(Throwable cause) {
        super(cause);
    }
}
