package com.ohgimduir.jaray.db.domain.repository;

import com.ohgimduir.jaray.db.domain.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
