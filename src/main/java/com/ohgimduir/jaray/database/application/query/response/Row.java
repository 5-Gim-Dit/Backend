package com.ohgimduir.jaray.database.application.query.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Row {

    private List<String> values = new ArrayList<>();

    public void add(String value) {
        values.add(value);
    }

}