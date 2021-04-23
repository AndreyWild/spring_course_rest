package com.wild.spring.rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionGlobalHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException( // Выбрасывает исключение по id
                                                                  NoSuchEmployeeException exception){
        EmployeeIncorrectData data= new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException( // Выбрасывает исключение по некорректному id
                                                                  Exception exception){
        EmployeeIncorrectData data= new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
