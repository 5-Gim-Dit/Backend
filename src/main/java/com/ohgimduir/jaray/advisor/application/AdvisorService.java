package com.ohgimduir.jaray.advisor.application;

import com.ohgimduir.jaray.advisor.application.request.ChatGPTQuery;
import com.ohgimduir.jaray.advisor.application.response.ChatGPTMessage;
import com.ohgimduir.jaray.advisor.application.response.ChatGptResponse;
import com.ohgimduir.jaray.advisor.application.response.QuestionRequest;
import com.ohgimduir.jaray.advisor.infra.ChatGPTConfig;
import com.ohgimduir.jaray.advisor.infra.property.ChatGPTProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdvisorService {

    private final RestTemplate restTemplate;
    private final ChatGPTProperty chatGPTProperty;

    public HttpEntity<ChatGPTQuery> buildHttpEntity(ChatGPTQuery chatGptRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGPTConfig.MEDIA_TYPE));
        httpHeaders.add(ChatGPTConfig.AUTHORIZATION, ChatGPTConfig.BEARER + chatGPTProperty.getKey());
        return new HttpEntity<>(chatGptRequest, httpHeaders);
    }

    public ChatGptResponse getQueryAnswer(QuestionRequest request) {
        List<ChatGPTMessage> messages = new ArrayList<>();
        messages.add(ChatGPTMessage.builder()
                .role(ChatGPTConfig.ROLE)
                .content(request.getQuestion())
                .build());
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGPTQuery(
                                ChatGPTConfig.CHAT_MODEL,
                                ChatGPTConfig.MAX_TOKEN,
                                ChatGPTConfig.TEMPERATURE,
                                ChatGPTConfig.STREAM,
                                messages
                                //ChatGptConfig.TOP_P
                        )
                )
        );

    }

    public ChatGptResponse getResponse(HttpEntity<ChatGPTQuery> chatGptRequestHttpEntity){

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(60 * 1000);
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGPTConfig.CHAT_URL,
                chatGptRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }
}