package com.numble.performancereservation.performance;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.numble.performancereservation.exception.RLockUnAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, RLockUnAvailableException.class})
    public ApiResult<String> handleException(Exception e) {
        log.error("exception occurred : {}", e.getMessage());
        return new ApiResult<>(e.getMessage());
    }
}
