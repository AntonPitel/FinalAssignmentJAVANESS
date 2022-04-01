package sk.ness.academy.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);

    }
}
