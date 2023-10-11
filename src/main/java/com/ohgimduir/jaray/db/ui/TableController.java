package com.ohgimduir.jaray.db.ui;

import com.ohgimduir.jaray.db.application.command.CommandTableService;
import com.ohgimduir.jaray.db.application.command.dto.request.CreateTableRequest;
import com.ohgimduir.jaray.db.application.command.dto.response.TableCreatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/table")
@RequiredArgsConstructor
public class TableController {

    private final CommandTableService commandTableService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TableCreatedResponse create(@RequestBody CreateTableRequest request, @PathVariable long id) {
        return commandTableService.create(id, request);
    }

}