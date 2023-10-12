package com.ohgimduir.jaray.database.ui;

import com.ohgimduir.jaray.database.application.command.CommandColumnService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
