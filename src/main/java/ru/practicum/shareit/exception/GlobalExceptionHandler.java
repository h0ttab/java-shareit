package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ErrorResponse handleEmailAlreadyExistsException(Exception exception) {
        return new ErrorResponse(409, exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(Exception exception) {
        return new ErrorResponse(404, exception);
    }

    public record ErrorResponse(int statusCode, Exception exception) {
        public ErrorResponse {
            log.error(exception.getMessage());
        }
    }
}
