package com.ohgimduir.jaray.database.application.command;

import com.ohgimduir.jaray.common.annotations.CommandService;
import com.ohgimduir.jaray.database.application.command.request.CreateDatabaseRequest;
import com.ohgimduir.jaray.database.domain.repository.DatabaseRepository;
import com.ohgimduir.jaray.auth.security.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandDatabaseService {

    private final DatabaseRepository dataBaseRepository;
    private final SecurityHelper securityHelper;

    public void create(CreateDatabaseRequest request) {
        dataBaseRepository.save(request.toDomain(securityHelper.getMemberId()));
    }

}