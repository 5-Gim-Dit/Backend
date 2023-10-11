package com.ohgimduir.jaray.db.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_value")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private Long columnId;

    @Builder
    public Value(String value, Long columnId) {
        this.value = value;
        this.columnId = columnId;
    }

}