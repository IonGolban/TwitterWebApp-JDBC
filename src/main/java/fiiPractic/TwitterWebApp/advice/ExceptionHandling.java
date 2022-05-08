package fiiPractic.TwitterWebApp.advice;

import fiiPractic.TwitterWebApp.advice.exception.NotUniqueException;
import fiiPractic.TwitterWebApp.advice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handle(NotFoundException exception){
        ErrorResponse error = new ErrorResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    @ExceptionHandler({NotUniqueException.class})
    public ResponseEntity<ErrorResponse> handle(NotUniqueException exception){
        ErrorResponse error = new ErrorResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
