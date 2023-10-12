package com.ohgimduir.jaray.advisor.application.request;

public record ChatGPTQuery(
        String model,
        String prompt,
        int temperature,
        int max_tokens
) {}