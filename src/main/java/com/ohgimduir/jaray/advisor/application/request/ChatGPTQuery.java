package com.ohgimduir.jaray.advisor.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohgimduir.jaray.advisor.application.response.ChatGPTMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGPTQuery implements Serializable {
    private String model;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    private Boolean stream;
    private List<ChatGPTMessage> messages;

    @Builder
    public ChatGPTQuery(String model, Integer maxTokens, Double temperature,
                          Boolean stream, List<ChatGPTMessage> messages
    ) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
    }
}