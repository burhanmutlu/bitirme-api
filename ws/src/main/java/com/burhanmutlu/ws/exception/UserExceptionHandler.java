package com.burhanmutlu.ws.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Controller + responsebody
public class UserExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        if(exception instanceof UserNotFoundException) {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }


}
