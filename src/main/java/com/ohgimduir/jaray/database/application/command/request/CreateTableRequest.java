package com.ohgimduir.jaray.database.application.command.request;

import com.ohgimduir.jaray.database.domain.entity.Table;

import java.util.List;

public record CreateTableRequest(
        String tableName,
        List<CreateColumnRequest> columRequests
) {
    public Table toDomain(Long databaseId) {
        return Table.ExceptIdBuilder()
                .name(tableName)
                .databaseId(databaseId)
                .build();
    }
}