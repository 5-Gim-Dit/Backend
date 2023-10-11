package com.ohgimduir.jaray.database.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

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

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Table(String name, Long databaseId) {
        Assert.notNull(name, "Name can not be null");
        Assert.notNull(databaseId, "DataBaseId can not be null");

        this.name = name;
        this.databaseId = databaseId;
    }

}