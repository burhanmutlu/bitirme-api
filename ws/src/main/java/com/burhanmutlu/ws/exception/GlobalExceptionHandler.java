package com.burhanmutlu.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DisabledException.class, AccessDeniedException.class})
    ResponseEntity<?> handleDisabledException(Exception exception, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        if(exception instanceof DisabledException) {
            errorResponse.setStatus(401);
        } else if (exception instanceof AccessDeniedException){
            errorResponse.setStatus(403);
        }

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);

    }

}
