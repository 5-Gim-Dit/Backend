package com.ohgimduir.jaray.auth.security.oauth.dto;

import com.ohgimduir.jaray.member.domain.type.LoginType;
import com.ohgimduir.jaray.member.domain.entity.Member;

public record MemberProfile (
        long socialId,
        String nickname,
        String imgUrl,
        LoginType loginType) {
    public Member toMember() {
        return Member.ExceptIdBuilder()
                .socialId(socialId)
                .nickname(nickname)
                .imgUrl(imgUrl)
                .loginType(loginType)
                .build();
    }
}