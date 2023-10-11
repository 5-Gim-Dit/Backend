package com.ohgimduir.jaray.db.domain.repository;

import com.ohgimduir.jaray.db.domain.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueRepository extends JpaRepository<Value, Long> {

    List<Value> findByColumnId(long columnId);

}
