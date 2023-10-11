package com.ohgimduir.jaray.db.application.command;

import com.ohgimduir.jaray.db.domain.entity.Column;
import com.ohgimduir.jaray.db.domain.entity.Value;
import com.ohgimduir.jaray.db.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.db.domain.repository.ValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandValueService {

    private final ColumnRepository columnRepository;
    private final ValueRepository valueRepository;

    public void create(long tableId, List<String> values) {
        List<Column> columns = columnRepository.findByTableId(tableId);

        IntStream.range(0, columns.size())
                .mapToObj(i -> Value.builder()
                        .columnId(columns.get(i).getId())
                        .value(values.get(i))
                        .build())
                .forEach(valueRepository::save);
    }

}