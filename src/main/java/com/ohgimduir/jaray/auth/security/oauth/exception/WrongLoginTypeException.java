package com.ohgimduir.jaray.auth.security.oauth.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(403, "Login Type is wrong");
    }

}