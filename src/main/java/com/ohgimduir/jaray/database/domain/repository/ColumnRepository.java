package com.ohgimduir.jaray.database.domain.repository;

import com.ohgimduir.jaray.database.domain.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Long> {

    List<Column> findByTableId(long tableId);

}