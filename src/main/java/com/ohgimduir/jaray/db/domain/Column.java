package com.ohgimduir.jaray.db.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_column")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @jakarta.persistence.Column(name = "column_id")
    private Long id;

    private String name;

    private Long tableId;

    @Builder
    public Column(String name, Long tableId) {
        this.name = name;
        this.tableId = tableId;
    }
}
