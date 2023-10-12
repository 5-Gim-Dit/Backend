package com.ohgimduir.jaray.advisor.application.response;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@Getter
public class ChatGPTMessage implements Serializable {
    private String role;
    private String content;

    @Builder
    public ChatGPTMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}