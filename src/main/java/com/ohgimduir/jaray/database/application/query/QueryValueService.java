package com.ohgimduir.jaray.database.application.query;

import com.ohgimduir.jaray.common.annotations.QueryService;
import com.ohgimduir.jaray.common.response.CommonResponse;
import com.ohgimduir.jaray.database.application.query.response.RowsResponse;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Value;
import com.ohgimduir.jaray.database.domain.exception.ColumnNotFoundException;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@QueryService
@RequiredArgsConstructor
public class QueryValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public RowsResponse getAll(long tableId) {
        Map<String, List<String>> response = new HashMap<>();

        List<Column> columns = columnRepository.findByTableId(tableId);

        for (Column column : columns) {
            List<String> values = valueRepository.findByColumnId(column.getId()).stream()
                    .map(Value::getValue)
                    .toList();
            response.put(column.getName(), values);
        }

        return RowsResponse.of(response);
    }

    public RowsResponse getByValue(long columnId, String value) {
        Column column = columnRepository.findById(columnId)
                .orElseThrow(() -> ColumnNotFoundException.EXCEPTION);

        List<Value> searchedValues = valueRepository.findByColumnIdAndValue(columnId, value);

        List<Value> allValues = valueRepository.findByColumnId(columnId);

        List<Long> indexes = IntStream.range(0, allValues.size())
                .filter(i -> searchedValues.contains(allValues.get(i)))
                .mapToObj(i -> (long) i).toList();

        List<Column> columns = columnRepository.findByTableId(column.getTableId());

        Map<String, List<String>> response = new HashMap<>();
        //perfect

        for(Column col : columns) {
            List<Value> values = valueRepository.findByColumnId(col.getId());

            List<String> result = new ArrayList<>();
            for (Long aLong : indexes) {
                int index = Math.toIntExact(aLong);
                result.add(values.get(index).getValue());
            }

            response.put(col.getName(), result);
        }

        return RowsResponse.of(response);
    }

    public RowsResponse getByColumns(List<Long> columnsIds) {
        Map<String, List<String>> response = new HashMap<>();

        List<Column> columns = columnsIds.stream()
                .map(id -> columnRepository.findById(id).orElseThrow())
                .toList();

        for (Column column : columns) {
            List<String> values = valueRepository.findByColumnId(column.getId()).stream()
                    .map(Value::getValue)
                    .toList();
            response.put(column.getName(), values);
        }

        return RowsResponse.of(response);
    }

}