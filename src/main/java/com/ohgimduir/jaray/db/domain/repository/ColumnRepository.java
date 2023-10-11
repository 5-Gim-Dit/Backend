package com.ohgimduir.jaray.db.domain.repository;

import com.ohgimduir.jaray.db.domain.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Long> {

    List<Column> findByTableId(Long id);
}
