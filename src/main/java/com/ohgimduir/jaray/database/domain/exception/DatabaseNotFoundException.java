package com.ohgimduir.jaray.database.domain.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class DatabaseNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new DatabaseNotFoundException();

    private DatabaseNotFoundException() {
        super(404, "DataBase not found");
    }

}