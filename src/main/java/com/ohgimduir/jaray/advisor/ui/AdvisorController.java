package com.ohgimduir.jaray.advisor.ui;

import com.ohgimduir.jaray.advisor.application.AdvisorService;
import com.ohgimduir.jaray.advisor.application.response.ChatGptResponse;
import com.ohgimduir.jaray.advisor.application.response.QuestionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "챗 지피 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/gpt")
public class AdvisorController {

    private final AdvisorService advisorService;

    @PostMapping("/query")
    @Operation(description = "챗 지피티에게 질문하기")
    public String callQueryApi(@RequestBody QuestionRequest request) {

        ChatGptResponse response = null;

        try {
            response = advisorService.getQueryAnswer(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.getChoices().get(0).getMessage().getContent();
    }
}