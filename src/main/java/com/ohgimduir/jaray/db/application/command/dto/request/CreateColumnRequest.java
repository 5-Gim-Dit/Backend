package com.ohgimduir.jaray.db.application.command.dto.request;

import com.ohgimduir.jaray.db.domain.type.ColumnType;

public record CreateColumnRequest(
        String name,
        ColumnType type
) {}