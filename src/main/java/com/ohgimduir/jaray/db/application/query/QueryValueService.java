package com.ohgimduir.jaray.db.application.query;

import com.ohgimduir.jaray.db.domain.entity.Column;
import com.ohgimduir.jaray.db.domain.entity.Value;
import com.ohgimduir.jaray.db.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.db.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public Map<String, List<String>> getAll(long tableId) {
        List<Column> columns = columnRepository.findByTableId(tableId);

        Map<String, List<String>> response = new HashMap<>();
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