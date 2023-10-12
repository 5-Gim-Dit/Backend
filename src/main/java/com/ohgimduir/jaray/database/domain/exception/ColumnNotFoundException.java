package com.ohgimduir.jaray.database.domain.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class ColumnNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ColumnNotFoundException();

    private ColumnNotFoundException() {
        super(404, "Column not found");
    }

}