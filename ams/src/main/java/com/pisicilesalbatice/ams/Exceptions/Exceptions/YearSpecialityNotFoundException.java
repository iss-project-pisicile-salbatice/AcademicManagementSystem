package com.pisicilesalbatice.ams.Exceptions.Exceptions;

public class YearSpecialityNotFoundException extends RuntimeException
{
    public YearSpecialityNotFoundException(String message) {
        super(message);
    }

    public YearSpecialityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public YearSpecialityNotFoundException(Throwable cause) {
        super(cause);
    }
}
