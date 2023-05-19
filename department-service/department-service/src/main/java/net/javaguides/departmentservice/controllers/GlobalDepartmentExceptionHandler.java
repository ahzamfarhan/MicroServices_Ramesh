package net.javaguides.departmentservice.controllers;

import net.javaguides.departmentservice.exceptions.DepartmentCreationException;
import net.javaguides.departmentservice.exceptions.DepartmentNotFoundException;
import net.javaguides.departmentservice.exceptions.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//https://www.toptal.com/java/spring-boot-rest-api-error-handling
@ControllerAdvice
public class GlobalDepartmentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFound(
            DepartmentNotFoundException departmentNotFoundException,
            WebRequest webRequest) {

            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage(departmentNotFoundException.getMessage());
            errorDetails.setStatusCode(HttpStatus.NOT_FOUND.name());
            errorDetails.setLocalDateTime(LocalDateTime.now());

            ResponseEntity responseEntity =
                    new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

            return responseEntity;
    }

    @ExceptionHandler(DepartmentCreationException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeCreation(
            DepartmentCreationException departmentCreationException,
            WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(departmentCreationException.getMessage());
        errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
        errorDetails.setLocalDateTime(LocalDateTime.now());

        ResponseEntity responseEntity =
                new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}