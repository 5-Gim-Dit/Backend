package com.ohgimduir.jaray.member.domain.entity;

import com.ohgimduir.jaray.member.domain.type.LoginType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "tbl_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private long socialId;

    private String nickname;

    private String imgUrl;

    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Member(Long socialId, String nickname, String imgUrl, LoginType loginType) {
        Assert.notNull(socialId, "SocialId can not be null");
        Assert.notNull(nickname, "Name can not be null");
        Assert.notNull(imgUrl, "ImgUrl can not be null");
        Assert.notNull(loginType, "LoginType can not be null");

        this.socialId = socialId;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.loginType = loginType;
    }

    public void updateOnLogin(String nickname, String imgUrl) {
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }

}