package com.ohgimduir.jaray.database.domain.entity;

import com.ohgimduir.jaray.database.domain.type.ColumnType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.util.Assert;

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

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Column(String name, ColumnType type, Long tableId) {
        Assert.notNull(name, "Name can not be null");
        Assert.notNull(type, "ColumnType can not be null");
        Assert.notNull(tableId, "TableId can not be null");

        this.name = name;
        this.type = type;
        this.tableId = tableId;
    }
}
