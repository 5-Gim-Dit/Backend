package com.ohgimduir.jaray.db.application.command.dto.response;

import java.util.List;

public record TableCreatedResponse(
        long tableId,
        List<Long> columnIds) {}