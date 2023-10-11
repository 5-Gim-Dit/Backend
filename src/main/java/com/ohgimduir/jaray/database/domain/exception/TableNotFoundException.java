package com.ohgimduir.jaray.database.domain.exception;

import com.ohgimduir.jaray.common.exception.CustomException;

public class TableNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new TableNotFoundException();

    private TableNotFoundException() {
        super(404, "DataBase not found");
    }

}