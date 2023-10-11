package com.ohgimduir.jaray.database.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.util.Assert;

@Entity
@Getter
@Table(name = "tbl_database")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Long memberId;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Database(String name, Long memberId) {
        Assert.notNull(name, "Name can not be null");
        Assert.notNull(memberId, "MemberId can not be null");

        this.name = name;
        this.memberId = memberId;
    }
}
