package com.ohgimduir.jaray.database.application.command.request;

import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.type.ColumnType;

public record CreateColumnRequest(
        String name,
        ColumnType type
) {
    public Column toDomain(Long tableId) {
        return Column.ExceptIdBuilder()
                .name(name)
                .type(type)
                .tableId(tableId)
                .build();
    }
}