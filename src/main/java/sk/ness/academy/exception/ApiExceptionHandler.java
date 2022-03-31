package sk.ness.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, internalServerError);
    }
}