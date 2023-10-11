package com.ohgimduir.jaray.database.application.query.response;

import com.ohgimduir.jaray.database.domain.entity.Column;
import com.ohgimduir.jaray.database.domain.entity.Table;

import java.util.List;

public record TableResponse(
        Table table,
        List<Column> columns
) {}