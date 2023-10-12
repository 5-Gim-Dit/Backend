package com.ohgimduir.jaray.advisor.application;

import com.ohgimduir.jaray.advisor.application.request.ChatGPTQuery;
import com.ohgimduir.jaray.advisor.application.response.ChatGPTMessage;
import com.ohgimduir.jaray.advisor.application.response.ChatGptResponse;
import com.ohgimduir.jaray.advisor.application.response.QuestionRequest;
import com.ohgimduir.jaray.advisor.infra.consts.ChatGPTConstants;
import com.ohgimduir.jaray.advisor.infra.property.ChatGPTProperties;
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
    private final ChatGPTProperties chatGPTProperties;

    public HttpEntity<ChatGPTQuery> buildHttpEntity(ChatGPTQuery chatGptRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGPTConstants.MEDIA_TYPE));
        httpHeaders.add(ChatGPTConstants.AUTHORIZATION, ChatGPTConstants.BEARER + chatGPTProperties.getKey());
        return new HttpEntity<>(chatGptRequest, httpHeaders);
    }

    public ChatGptResponse getQueryAnswer(QuestionRequest request) {
        List<ChatGPTMessage> messages = new ArrayList<>();
        messages.add(ChatGPTMessage.builder()
                .role(ChatGPTConstants.ROLE)
                .content(request.getQuestion())
                .build());
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGPTQuery(
                                ChatGPTConstants.CHAT_MODEL,
                                ChatGPTConstants.MAX_TOKEN,
                                ChatGPTConstants.TEMPERATURE,
                                ChatGPTConstants.STREAM,
                                messages
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
                ChatGPTConstants.CHAT_URL,
                chatGptRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }
}