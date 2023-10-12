package com.ohgimduir.jaray.database.ui;

import com.ohgimduir.jaray.database.application.command.CommandColumnService;
import com.ohgimduir.jaray.database.application.command.request.CreateColumnRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Column API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/column")
public class ColumnController {

    private final CommandColumnService commandColumnService;

    @DeleteMapping("/{columnId}")
    public void deleteColumn(@PathVariable long columnId) {
        commandColumnService.deleteColumn(columnId);
    }

    @PostMapping("/by/{tableId}")
    public void create(@PathVariable long tableId, @RequestBody CreateColumnRequest request) {
        commandColumnService.create(tableId, request);
    }

}
