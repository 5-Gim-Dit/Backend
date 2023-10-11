package com.ohgimduir.jaray.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomException extends RuntimeException {

    private final int status;

    private final String message;

}