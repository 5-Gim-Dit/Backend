package com.ohgimduir.jaray.database.application.command;

import com.ohgimduir.jaray.common.annotations.CommandService;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Value;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@CommandService
@RequiredArgsConstructor
public class CommandValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public void create(long tableId, List<String> values) {
        List<Column> columns = columnRepository.findByTableId(tableId);

        IntStream.range(0, columns.size())
                .mapToObj(i -> Value.ExceptIdBuilder()
                        .columnId(columns.get(i).getId())
                        .value(values.get(i))
                        .build())
                .forEach(valueRepository::save);
    }

}