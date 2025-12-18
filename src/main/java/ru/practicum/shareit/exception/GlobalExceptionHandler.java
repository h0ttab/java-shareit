package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ErrorResponse handleEmailAlreadyExistsException(Exception e) {
        log.error(e.getMessage());
        return new ErrorResponse(409, e.getMessage());
    }

    public record ErrorResponse(int statusCode, String error) {}
}
