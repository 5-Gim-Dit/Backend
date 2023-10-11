package com.ohgimduir.jaray.security.oauth.helper;

import com.ohgimduir.jaray.member.domain.Member;
import com.ohgimduir.jaray.security.oauth.principal.CustomMemberDetails;
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