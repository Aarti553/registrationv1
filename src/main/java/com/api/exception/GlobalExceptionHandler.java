package com.api.exception;

import com.api.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//this will handle only ResourceNotFoundException.
    public ResponseEntity<ErrorDto> resourceNotFound(
            ResourceNotFoundException r,
            WebRequest request
    ){
        ErrorDto error = new ErrorDto(r.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);//this will taken care by SpringBoot.
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>handleGlobalException(
            Exception e
    ){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//all are runtime Exception.
/*
differnce between @controller,@RestController and @ControllerAdvice
@Controller-acts as a mediator between view and the backend business logic.
@RestController-helps us to build Api in SpringBoot.
@ControllerAdvice-This is used for handling Exceptions.
 */