package com.ohgimduir.jaray.db.domain;

import com.ohgimduir.jaray.db.domain.type.ColumnType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
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
