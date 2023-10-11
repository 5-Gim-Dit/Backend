package com.ohgimduir.jaray.database.application.command;

import com.ohgimduir.jaray.common.annotations.CommandService;
import com.ohgimduir.jaray.database.application.command.request.CreateColumnRequest;
import com.ohgimduir.jaray.database.application.command.request.CreateTableRequest;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@CommandService
@RequiredArgsConstructor
public class CommandTableService {

    private final TableRepository tableRepository;
    private final ColumnRepository columnRepository;

    public void create(Long databaseId, CreateTableRequest request) {
        long tableId = tableRepository.save(request.toDomain(databaseId)).getId();

        columnRepository.saveAll(toColumnList(tableId, request.columRequests()));
    }

    private List<Column> toColumnList(long tableId, List<CreateColumnRequest> requests) {
        return requests.stream()
                .map(request -> request.toDomain(tableId))
                .toList();
    }

}