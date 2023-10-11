package com.ohgimduir.jaray.member.domain.exception;

import com.ohgimduir.jaray.common.exception.CustomException;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberNotFoundException();

    private MemberNotFoundException() {
        super(404, "Member Not Found");
    }

}