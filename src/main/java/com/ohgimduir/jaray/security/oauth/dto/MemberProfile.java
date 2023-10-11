package com.ohgimduir.jaray.security.oauth.dto;

import com.ohgimduir.jaray.member.domain.LoginType;
import com.ohgimduir.jaray.member.domain.Member;

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