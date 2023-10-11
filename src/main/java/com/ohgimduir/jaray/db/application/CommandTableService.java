package com.ohgimduir.jaray.db.application;

import com.ohgimduir.jaray.db.domain.Column;
import com.ohgimduir.jaray.db.domain.ColumnRepository;
import com.ohgimduir.jaray.db.domain.Table;
import com.ohgimduir.jaray.db.domain.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandTableService {

    private final TableRepository tableRepository;
    private final ColumnRepository columnRepository;

    public Long create(Long id, CreateTableRequest request) {
        Long tableId = tableRepository.save(
                Table.builder()
                        .name(request.tableName())
                        .dataBaseId(id)
                        .build()
        ).getId();

        List<Column> columns = request.createColumnRequest().stream()
                .map(column ->
                    Column.builder()
                            .name(column.name())
                            .tableId(tableId)
                            .build()
                )
                .toList();

        columnRepository.saveAll(columns);

        return tableId;
    }
}
