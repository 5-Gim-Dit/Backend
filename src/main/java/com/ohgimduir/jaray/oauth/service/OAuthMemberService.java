package com.ohgimduir.jaray.oauth.service;

import com.ohgimduir.jaray.member.domain.LoginType;
import com.ohgimduir.jaray.member.domain.Member;
import com.ohgimduir.jaray.member.domain.MemberRepository;
import com.ohgimduir.jaray.oauth.principal.CustomMemberDetails;
import com.ohgimduir.jaray.oauth.dto.MemberProfile;
import com.ohgimduir.jaray.oauth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthMemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        final Map<String, Object> attributes = super.loadUser(oAuth2UserRequest).getAttributes();

        final LoginType loginType = getLoginType(oAuth2UserRequest);

        final MemberProfile memberProfile = OAuthAttributes.toProfile(loginType, attributes);

        Member member = memberRepository.findBySocialId(memberProfile.socialId())
                .orElse(null);

        if(member == null) {
            member = memberRepository.save(memberProfile.toMember());
        } else {
            member.updateOnLogin(memberProfile.nickname(), memberProfile.imgUrl());
        }

        return CustomMemberDetails.create(member, attributes);
    }

    private LoginType getLoginType(final OAuth2UserRequest oAuth2UserRequest) {
        return LoginType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
    }

}