package com.ohgimduir.jaray.oauth.helper;

import com.ohgimduir.jaray.member.domain.Member;
import com.ohgimduir.jaray.oauth.principal.CustomMemberDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    public Member getMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

}