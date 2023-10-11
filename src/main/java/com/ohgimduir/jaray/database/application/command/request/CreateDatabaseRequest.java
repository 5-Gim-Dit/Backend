package com.ohgimduir.jaray.database.application.command.request;

import com.ohgimduir.jaray.database.domain.entity.Database;

public record CreateDatabaseRequest(
        String name
) {
    public Database toDomain(Long memberId) {
        return Database.ExceptIdBuilder()
                .name(name)
                .memberId(memberId)
                .build();
    }
}