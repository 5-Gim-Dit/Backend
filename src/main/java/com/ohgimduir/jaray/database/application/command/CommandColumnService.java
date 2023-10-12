package com.ohgimduir.jaray.database.application.command;

import com.ohgimduir.jaray.common.annotations.CommandService;

import com.ohgimduir.jaray.database.application.command.request.CreateColumnRequest;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandColumnService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public void deleteColumn(long columnId) {
        valueRepository.deleteByColumnId(columnId);
        columnRepository.deleteById(columnId);
    }

    public void create(long tableId, CreateColumnRequest request) {
        columnRepository.save(Column.ExceptIdBuilder()
                .name(request.name())
                .type(request.type())
                .tableId(tableId)
                .build()).getId();
    }

}