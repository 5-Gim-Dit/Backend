package com.ohgimduir.jaray.db.domain;

import com.ohgimduir.jaray.member.domain.Member;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "database_id")
    private Long id;

    private Long memberId;

    @Builder
    public DataBase(Long memberId) {
        this.memberId = memberId;
    }
}
