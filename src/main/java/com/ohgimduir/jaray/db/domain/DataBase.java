package com.ohgimduir.jaray.db.domain;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "database_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private Long memberId;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public DataBase(String name, Long memberId) {
        this.name = name;
        this.memberId = memberId;
    }
}
