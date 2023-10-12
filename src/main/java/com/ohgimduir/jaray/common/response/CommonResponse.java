package com.ohgimduir.jaray.common.response;

public record CommonResponse <T> (
        T data
) {}