package com.ohgimduir.jaray.database.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@jakarta.persistence.Table(name = "tbl_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long databaseId;

    @CreatedDate
    private LocalDate createDate;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Table(String name, Long databaseId) {
        Assert.notNull(name, "Name can not be null");
        Assert.notNull(databaseId, "DataBaseId can not be null");

        this.name = name;
        this.databaseId = databaseId;
    }

}