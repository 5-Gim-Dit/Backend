package com.ohgimduir.jaray.database.application.query;

import com.ohgimduir.jaray.common.annotations.QueryService;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Value;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.TableRepository;
import com.ohgimduir.jaray.database.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QueryService
@RequiredArgsConstructor
public class QueryValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;
    private final TableRepository tableRepository;

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

    public Map<String, List<String>> getByValue(long columnId, String value) {
        Column column = columnRepository.findById(columnId)
                .orElseThrow();

        List<Value> values1 = valueRepository.findByColumnIdAndValue(columnId, value);
        List<Value> values2 = valueRepository.findByColumnId(columnId);

        List<Long> indexs = new ArrayList<>();
        for (int i = 0; i < values2.size(); i++) {
            if(values1.contains(values2.get(i))) {
                indexs.add((long)i);
            }
        }

        List<Column> columns = columnRepository.findByTableId(column.getTableId());

        Map<String, List<String>> response = new HashMap<>();

        for(Column col : columns) {
            List<Value> values = valueRepository.findByColumnId(col.getId());

            response.put(col.getName(), values.stream()
                    .filter(a -> indexs.contains(a.getId()))
                    .map(Value::getValue)
                    .toList()
            );
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