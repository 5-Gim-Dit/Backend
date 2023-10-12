package com.ohgimduir.jaray.advisor.ui;

import com.ohgimduir.jaray.advisor.application.AdvisorService;
import com.ohgimduir.jaray.advisor.application.request.ChatGPTQuery;
import com.ohgimduir.jaray.advisor.application.response.ChatGptResponse;
import com.ohgimduir.jaray.advisor.application.response.QuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gpt")
public class AdvisorController {

    private final AdvisorService advisorService;

    @PostMapping("/query")
    public String callQueryApi(@RequestBody QuestionRequest request) {

        ChatGptResponse response = null;

        try {
            response = advisorService.getQueryAnswer(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
}