package com.pisicilesalbatice.ams.Exceptions;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse
{
    private final int status;
    private final String message;

    public ErrorResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public int getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }
}
