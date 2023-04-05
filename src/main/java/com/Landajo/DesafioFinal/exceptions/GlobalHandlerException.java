package com.Landajo.DesafioFinal.exceptions;

import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientNotFoundException;
import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(IdNotValidException.class)
    public ResponseEntity<?> idNotValidException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    //Client Exceptions
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<?> clientAlreadyExistsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Product Exceptions
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<?> productAlreadyExistsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}

