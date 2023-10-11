package com.ohgimduir.jaray.db.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Table(name = "tbl_database")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Long memberId;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public DataBase(String name, Long memberId) {
        Objects.requireNonNull(name, "Name can not be null");

        this.name = name;
        this.memberId = memberId;
    }
}
