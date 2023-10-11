package com.ohgimduir.jaray.db.ui;

import com.ohgimduir.jaray.db.application.command.CommandValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/value")
public class ValueController {

    private final CommandValueService commandValueService;

    @PostMapping("/{id}")
    public void create(@PathVariable Long id, List<String> values) {
        commandValueService.create(id, values);
    }
}
