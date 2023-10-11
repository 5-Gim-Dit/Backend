package com.ohgimduir.jaray.db.domain;

import com.ohgimduir.jaray.db.domain.type.ColumnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@jakarta.persistence.Table(name = "tbl_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ColumnType type;

    private Long dataBaseId;

    @Builder
    public Table(String name, ColumnType type, Long dataBaseId) {
        this.name = name;
        this.type = type;
        this.dataBaseId = dataBaseId;
    }
}
