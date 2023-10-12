package com.ohgimduir.jaray.database.application.command;

import com.ohgimduir.jaray.common.annotations.CommandService;
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
}
