package com.rocktech.hospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StaffNotFound.class)
    public ResponseEntity<ErrorMessage> staffNotFound(StaffNotFound notFound, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, notFound.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(PatientNotFound.class)
    public ResponseEntity<ErrorMessage> patientNotFound(PatientNotFound notFound, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, notFound.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(UUIDNotFound.class)
    public ResponseEntity<ErrorMessage> uUIDNotFound(UUIDNotFound notFound, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, notFound.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(ForbiddenResponse.class)
    public ResponseEntity<ErrorMessage> forbiddenResponse(ForbiddenResponse forbiddenResponse, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN, forbiddenResponse.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }
}
