package com.ohgimduir.jaray.auth.security.helper;

import com.ohgimduir.jaray.auth.security.oauth.principal.CustomMemberDetails;
import com.ohgimduir.jaray.member.domain.entity.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    public Member getMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

    public long getMemberId() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember().getId();
    }

}