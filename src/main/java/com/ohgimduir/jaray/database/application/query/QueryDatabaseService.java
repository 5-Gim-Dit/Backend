package com.ohgimduir.jaray.database.application.query;

import com.ohgimduir.jaray.common.annotations.QueryService;
import com.ohgimduir.jaray.common.response.CommonResponse;
import com.ohgimduir.jaray.database.domain.entity.Database;
import com.ohgimduir.jaray.database.domain.repository.DatabaseRepository;
import com.ohgimduir.jaray.auth.security.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryDatabaseService {

    private final DatabaseRepository dataBaseRepository;
    private final SecurityHelper securityHelper;

    public CommonResponse<List<Database>> getMy() {
        return new CommonResponse<>(dataBaseRepository.findByMemberId(securityHelper.getMemberId()));
    }

}