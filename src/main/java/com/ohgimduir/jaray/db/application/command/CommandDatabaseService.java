package com.ohgimduir.jaray.db.application.command;

import com.ohgimduir.jaray.db.application.command.dto.request.CreateDatabaseRequest;
import com.ohgimduir.jaray.db.domain.entity.DataBase;
import com.ohgimduir.jaray.db.domain.repository.DataBaseRepository;
import com.ohgimduir.jaray.security.oauth.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandDatabaseService {

    private final DataBaseRepository dataBaseRepository;
    private final SecurityHelper securityHelper;

    public Long create(CreateDatabaseRequest request) {
        DataBase dataBase = dataBaseRepository.save(DataBase.ExceptIdBuilder()
                .name(request.name())
                .memberId(securityHelper.getMemberId())
                .build());

        return dataBase.getId();
    }

}