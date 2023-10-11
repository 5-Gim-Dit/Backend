package com.ohgimduir.jaray.database.domain.repository;

import com.ohgimduir.jaray.database.domain.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findByDatabaseId(long databaseId);

}