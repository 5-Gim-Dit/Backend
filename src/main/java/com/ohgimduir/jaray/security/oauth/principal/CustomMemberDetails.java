package com.ohgimduir.jaray.security.oauth.principal;

import com.ohgimduir.jaray.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomMemberDetails implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    private CustomMemberDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public static CustomMemberDetails create(Member member, Map<String, Object> attributes) {
        return new CustomMemberDetails(member, attributes);
    }

    public static CustomMemberDetails create(Member member) {
        return new CustomMemberDetails(member);
    }

    @Override
    public String getName() {
        return String.valueOf(member.getSocialId());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}