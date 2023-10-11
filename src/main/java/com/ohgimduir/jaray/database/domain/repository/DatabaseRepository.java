package com.ohgimduir.jaray.database.domain.repository;

import com.ohgimduir.jaray.database.domain.entity.Database;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseRepository extends JpaRepository<Database, Long> {

    List<Database> findByMemberId(long memberId);

}
