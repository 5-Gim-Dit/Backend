package com.ohgimduir.jaray.advisor.application.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class QuestionRequest implements Serializable {

    private String question;
}
