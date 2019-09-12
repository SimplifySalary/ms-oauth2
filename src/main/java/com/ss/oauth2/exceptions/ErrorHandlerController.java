package com.ss.oauth2.exceptions;

import com.ss.oauth2.model.ErrorResponse;
import com.ss.oauth2.model.FieldValidationErrorResponse;
import com.ss.oauth2.model.ValidationBadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author biandra
 */
@Slf4j
@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";

    @ExceptionHandler(BindingResultException.class)
    public ResponseEntity<ErrorResponse> handle(BindingResultException ex, HttpServletRequest request) {
        log.error("Bad request, Error message: {}", ex.getMessage());
        return new ResponseEntity<>(buildResponse(ex.getMessage(), ex.getBindingResult()), this.getHttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotExistException.class)
    public ResponseEntity<ErrorResponse> handle(IdNotExistException ex, HttpServletRequest request) {
        log.error("Resource not found, Error message: {}", ex.getMessage());
        return new ResponseEntity<>(buildResponse(ex.getMessage()), this.getHttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintException ex, HttpServletRequest request) {
        log.error("Resource not found, Error message: {}", ex.getMessage());
        return new ResponseEntity<>(buildResponse(ex.getMessage()), this.getHttpHeaders(), HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        return headers;
    }

    private ErrorResponse buildResponse(String code) {
        return new ErrorResponse(code);
    }

    private ValidationBadRequest buildResponse(String message, BindingResult bindingResult) {
        List<FieldValidationErrorResponse> fieldErrors = bindingResult.getFieldErrors().stream().map(aFieldError -> new FieldValidationErrorResponse(
                aFieldError.getField(),
                aFieldError.getDefaultMessage())).collect(Collectors.toList());

        return new ValidationBadRequest(message, fieldErrors);
    }
}
