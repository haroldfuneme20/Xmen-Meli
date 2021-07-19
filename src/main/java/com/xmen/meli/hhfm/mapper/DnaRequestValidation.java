package com.xmen.meli.hhfm.mapper;

import com.xmen.meli.hhfm.model.MutantResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DnaRequestValidation {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleDnaNullDataException(MethodArgumentNotValidException methodArgumentNotValidException) {
        // handle validation exception
        ResponseEntity<MutantResponse> response;
        MutantResponse mutantResponse = new MutantResponse();
        mutantResponse.setIs_mutant(false);
        mutantResponse.setMessage("The body cannot be empty, please enter DNA for test");
        mutantResponse.setStatusCode("400");
        response = new ResponseEntity(mutantResponse, HttpStatus.BAD_REQUEST);
        return response;
    }
}
