package com.ohgimduir.jaray.database.ui;

import com.ohgimduir.jaray.database.application.command.CommandValueService;
import com.ohgimduir.jaray.database.application.query.QueryValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Value API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/value")
public class ValueController {

    private final CommandValueService commandValueService;
    private final QueryValueService queryValueService;

    @Operation(description = "Values(=Row) 생성")
    @PostMapping("/{tableId}")
    public void create(@PathVariable Long tableId, @RequestBody List<String> values) {
        commandValueService.create(tableId, values);
    }

    @Operation(description = "Values(=Row) 조회 by Table")
    @GetMapping("/{tableId}")
    public Map<String, List<String>> getAll(@PathVariable long tableId) {
        return queryValueService.getAll(tableId);
    }

}