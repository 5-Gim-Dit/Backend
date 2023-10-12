package com.ohgimduir.jaray.advisor.infra.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app.advisor")
public class ChatGPTProperty {

    private String key;
}
