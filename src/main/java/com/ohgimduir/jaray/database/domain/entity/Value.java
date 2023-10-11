package com.ohgimduir.jaray.database.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Value(String value, Long columnId) {
        Assert.notNull(value, "Value can not be null");
        Assert.notNull(columnId, "ColumnId can not be null");

        this.value = value;
        this.columnId = columnId;
    }

}