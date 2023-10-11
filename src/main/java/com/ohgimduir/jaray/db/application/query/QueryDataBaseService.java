package com.ohgimduir.jaray.db.application.query;

import com.ohgimduir.jaray.db.domain.entity.DataBase;
import com.ohgimduir.jaray.db.domain.repository.DataBaseRepository;
import com.ohgimduir.jaray.security.oauth.helper.SecurityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryDataBaseService {

    private final DataBaseRepository dataBaseRepository;
    private final SecurityHelper securityHelper;

    public List<DataBase> getMy() {
        return dataBaseRepository.findByMemberId(securityHelper.getMemberId());
    }

}