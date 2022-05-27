package com.pisicilesalbatice.ams.Exceptions;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(EnrollmentServiceException.class)
    protected ResponseEntity<Object> handleEnrollmentService(EnrollmentServiceException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(OptionalServiceException.class)
    protected ResponseEntity<Object> handleOptionalService(OptionalServiceException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(TeacherServiceException.class)
    protected ResponseEntity<Object> handleTeacherService(TeacherServiceException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(GradeNotFoundException.class)
    protected ResponseEntity<Object> handleGradeNotFound(GradeNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    protected ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    protected ResponseEntity<Object> handleTeacherNotFound(TeacherNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(YearSpecialityNotFoundException.class)
    protected ResponseEntity<Object> handleYearSpecialityNotFound(YearSpecialityNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // OptionalNotFoundException
    @ExceptionHandler(OptionalNotFoundException.class)
    protected ResponseEntity<Object> handleOptionalNotFound(OptionalNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
