package com.ohgimduir.jaray.database.application.query;

import com.ohgimduir.jaray.common.annotations.QueryService;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Value;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QueryService
@RequiredArgsConstructor
public class QueryValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public Map<String, List<String>> getAll(long tableId) {
        Map<String, List<String>> response = new HashMap<>();

        List<Column> columns = columnRepository.findByTableId(tableId);

        for (Column column : columns) {
            List<String> values = valueRepository.findByColumnId(column.getId()).stream()
                    .map(Value::getValue)
                    .toList();
            response.put(column.getName(), values);
        }

        return response;
    }

    public List<String> getByColumn(long columnId) {
        Column column = columnRepository.findById(columnId)
                .orElseThrow();

        return valueRepository.findByColumnId(column.getId()).stream()
                .map(Value::getValue)
                .toList();
    }

}