package com.ohgimduir.jaray.security.oauth.dto;

import com.ohgimduir.jaray.member.domain.LoginType;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public enum OAuthAttributes {

    KAKAO(LoginType.KAKAO, (attributes) -> {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return new MemberProfile(
                (long) attributes.get("id"),
                (String) kakaoProfile.get("nickname"),
                (String) kakaoProfile.get("profile_image_url"),
                LoginType.KAKAO);
        }
    );

    private final LoginType registration;
    private final Function<Map<String, Object>, MemberProfile> of;

    public static MemberProfile toProfile(LoginType registration, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registration.equals(provider.registration))
                .findFirst()
                .orElseThrow(/* todo exception 추가*/)
                .of.apply(attributes);
    }

}