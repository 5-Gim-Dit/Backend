package com.ohgimduir.jaray.security.oauth.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class OAuthFailException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(500, "OAuth failed");
    }

}