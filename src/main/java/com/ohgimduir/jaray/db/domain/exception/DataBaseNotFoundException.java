package com.ohgimduir.jaray.db.domain.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class DataBaseNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new DataBaseNotFoundException();

    private DataBaseNotFoundException() {
        super(404, "DataBase not found");
    }

}