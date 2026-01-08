package ru.practicum.shareit.exception;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ErrorResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        return new ErrorResponse(409, exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(404, exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse handleHandlerMethodValidationException(HandlerMethodValidationException exception) {
        String message = String.join("\n",
                Arrays.stream(exception.getDetailMessageArguments()).map(Object::toString).toList()
        );
        return new ErrorResponse(400, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ErrorResponse handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        return new ErrorResponse(400, exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(BadRequestException exception) {
        return new ErrorResponse(400, exception);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse handleForbiddenException(ForbiddenException exception) {
        return new ErrorResponse(403, exception);
    }

    @Setter
    @Getter
    public static class ErrorResponse {
        private final int statusCode;
        private final String message;

        public ErrorResponse(int statusCode, Exception exception) {
            log.error(exception.getMessage());
            this.statusCode = statusCode;
            this.message = exception.getMessage();
        }

        public ErrorResponse(int statusCode, String message) {
            log.error(message);
            this.statusCode = statusCode;
            this.message = message;
        }
    }
}
