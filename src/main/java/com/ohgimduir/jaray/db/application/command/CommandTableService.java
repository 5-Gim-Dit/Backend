package com.ohgimduir.jaray.db.application.command;

import com.ohgimduir.jaray.db.application.command.dto.request.CreateColumnRequest;
import com.ohgimduir.jaray.db.application.command.dto.request.CreateTableRequest;
import com.ohgimduir.jaray.db.application.command.dto.response.TableCreatedResponse;
import com.ohgimduir.jaray.db.domain.entity.Column;
import com.ohgimduir.jaray.db.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.db.domain.entity.Table;
import com.ohgimduir.jaray.db.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandTableService {

    private final TableRepository tableRepository;
    private final ColumnRepository columnRepository;

    public TableCreatedResponse create(Long id, CreateTableRequest request) {
        Long tableId = tableRepository.save(
                Table.builder()
                        .name(request.tableName())
                        .dataBaseId(id)
                        .build()
        ).getId();

        List<Long> columnIds = columnRepository.saveAll(asColumnList(tableId, request.createColumnRequest())).stream()
                .map(Column::getId)
                .toList();

        return new TableCreatedResponse(tableId, columnIds);
    }

    private List<Column> asColumnList(long tableId, List<CreateColumnRequest> requests) {
        return requests.stream()
                .map(column -> Column.builder()
                        .name(column.name())
                        .type(column.type())
                        .tableId(tableId)
                        .build())
                .toList();
    }

}