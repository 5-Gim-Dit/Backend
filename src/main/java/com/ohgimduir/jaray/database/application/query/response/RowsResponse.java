package com.ohgimduir.jaray.database.application.query.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RowsResponse(
        List<Row> rows
) {
    public static RowsResponse of(Map<String, List<String>> mapResponse) {
        List<Row> rows = new ArrayList<>();

        /* column size */
        int degree = mapResponse.size();

        List<String> columnList = mapResponse.keySet().stream().toList();

        /* 모든 컬럼의 Values */
        List<List<String>> valuesOfColumns = new ArrayList<>();

        for(int i = 0; i<degree; i++) {
            valuesOfColumns.add(mapResponse.get(columnList.get(i)));
        }

        /* row size */
        int rowSize = valuesOfColumns.get(0).size();

        for(int i = 0; i<rowSize; i++) {

            Row row = new Row();
            for(int j = 0; j<degree; j++) {
                row.add(valuesOfColumns.get(j).get(i));
            }

            rows.add(row);
        }

        return new RowsResponse(rows);
    }
}