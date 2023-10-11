package com.ohgimduir.jaray.db.application.command.dto.request;

import java.util.List;

public record CreateTableRequest(
        String tableName,
        List<CreateColumnRequest> createColumnRequest
) {}