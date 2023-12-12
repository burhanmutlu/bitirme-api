package com.burhanmutlu.ws.user.exception;

import javax.servlet.http.HttpServletRequest;

import com.burhanmutlu.ws.company.exception.CompanyNotFoundException;
import com.burhanmutlu.ws.shared.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Controller + responsebody
public class UserExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            CompanyNotFoundException.class,
            NullPointerException.class
    })
    public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        if(exception instanceof UserNotFoundException) {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (exception instanceof Exception) {
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else if (exception instanceof CompanyNotFoundException) {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if(exception instanceof NullPointerException) {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }


}
