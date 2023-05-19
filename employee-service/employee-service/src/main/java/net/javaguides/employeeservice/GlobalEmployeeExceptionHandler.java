package net.javaguides.employeeservice;

import net.javaguides.employeeservice.exceptions.EmployeeCreationException;
import net.javaguides.employeeservice.exceptions.EmployeeNotFoundException;
import net.javaguides.employeeservice.exceptions.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//https://www.toptal.com/java/spring-boot-rest-api-error-handling
@ControllerAdvice
public class GlobalEmployeeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFound(
            EmployeeNotFoundException employeeNotFoundException,
            WebRequest webRequest) {

            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage(employeeNotFoundException.getMessage());
            errorDetails.setStatusCode(HttpStatus.NOT_FOUND.name());
            errorDetails.setLocalDateTime(LocalDateTime.now());

            ResponseEntity responseEntity =
                    new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

            return responseEntity;
    }

    @ExceptionHandler(EmployeeCreationException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeCreation(
            EmployeeCreationException employeeCreationException,
            WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(employeeCreationException.getMessage());
        errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
        errorDetails.setLocalDateTime(LocalDateTime.now());

        ResponseEntity responseEntity =
                new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}
