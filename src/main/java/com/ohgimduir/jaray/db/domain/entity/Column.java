package com.ohgimduir.jaray.db.domain.entity;

import com.ohgimduir.jaray.db.domain.type.ColumnType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_column")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value =  EnumType.STRING)
    private ColumnType type;

    private Long tableId;

    @Builder
    public Column(String name, ColumnType type, Long tableId) {
        Objects.requireNonNull(name, "Name can not be null");

        this.name = name;
        this.type = type;
        this.tableId = tableId;
    }
}
