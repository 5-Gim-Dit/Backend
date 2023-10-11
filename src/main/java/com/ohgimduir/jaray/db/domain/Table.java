package com.ohgimduir.jaray.db.domain;

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

    private Long dataBaseId;

    @Builder
    public Table(String name, Long dataBaseId) {
        this.name = name;
        this.dataBaseId = dataBaseId;
    }
}
