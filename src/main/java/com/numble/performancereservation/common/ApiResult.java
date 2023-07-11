package com.numble.performancereservation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    long count;
    T result;

    public ApiResult(T result) {
        this.result = result;
    }
}
