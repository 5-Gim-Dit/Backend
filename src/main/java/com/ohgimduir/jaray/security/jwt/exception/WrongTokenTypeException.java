package com.ohgimduir.jaray.security.jwt.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class WrongTokenTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(400, "Token type is wrong");
    }

}