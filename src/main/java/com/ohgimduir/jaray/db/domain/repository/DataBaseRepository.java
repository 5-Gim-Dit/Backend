package com.ohgimduir.jaray.db.domain.repository;

import com.ohgimduir.jaray.db.domain.entity.DataBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataBaseRepository extends JpaRepository<DataBase, Long> {

    List<DataBase> findByMemberId(long memberId);

}
