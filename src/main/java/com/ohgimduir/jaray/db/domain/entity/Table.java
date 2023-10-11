package com.ohgimduir.jaray.db.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@jakarta.persistence.Table(name = "tbl_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long dataBaseId;

    @Builder
    public Table(String name, Long dataBaseId) {
        Objects.requireNonNull(name, "Name can not be null");

        this.name = name;
        this.dataBaseId = dataBaseId;
    }

}
