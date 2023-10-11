package com.ohgimduir.jaray.db.application;

import com.ohgimduir.jaray.db.domain.type.ColumnType;

import java.util.List;

public record CreateTableRequest(
        String tableName,
        List<CreateColumnRequest> createColumnRequest
) {}

record CreateColumnRequest(
        String name,
        ColumnType type
) {}