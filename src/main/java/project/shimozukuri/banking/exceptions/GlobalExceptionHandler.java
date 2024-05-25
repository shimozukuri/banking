package project.shimozukuri.banking.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(
            {AccessDeniedException.class,
                    org.springframework.security.access.AccessDeniedException.class}
    )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AppError catchAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError catchIllegalStateException(IllegalStateException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError catchResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new AppError(e.getMessage());
    }
}
