package com.ohgimduir.jaray.database.application.query;

import com.ohgimduir.jaray.common.annotations.QueryService;
import com.ohgimduir.jaray.common.response.CommonResponse;
import com.ohgimduir.jaray.database.application.query.response.TableResponse;
import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Table;
import com.ohgimduir.jaray.database.domain.exception.TableNotFoundException;
import com.ohgimduir.jaray.database.domain.repository.ColumnRepository;
import com.ohgimduir.jaray.database.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryTableService {

    private final TableRepository tableRepository;
    private final ColumnRepository columnRepository;

    public TableResponse getById(long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> TableNotFoundException.EXCEPTION);

        List<Column> columns = columnRepository.findByTableId(tableId);

        return new TableResponse(table, columns);
    }

    public CommonResponse<List<Table>> getByDatabase(long databaseId) {
        return new CommonResponse<>(tableRepository.findByDatabaseId(databaseId));
    }

}