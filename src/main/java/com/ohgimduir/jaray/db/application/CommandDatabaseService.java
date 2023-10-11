package com.ohgimduir.jaray.db.application;

import com.ohgimduir.jaray.db.domain.DataBase;
import com.ohgimduir.jaray.db.domain.DataBaseRepository;
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

    public Long create(CreateDataRequest request) {
        DataBase dataBase = dataBaseRepository.save(DataBase.ExceptIdBuilder()
                .name(request.databaseName())
                .memberId(securityHelper.getMemberId())
                .build());

        return dataBase.getId();
    }

}