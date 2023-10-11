package com.ohgimduir.jaray.db.ui;

import com.ohgimduir.jaray.db.application.command.CommandDatabaseService;
import com.ohgimduir.jaray.db.application.command.dto.request.CreateDatabaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/database")
@RequiredArgsConstructor
public class DataBaseController {

    private final CommandDatabaseService commandDatabaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CreateDatabaseRequest request) {
        return commandDatabaseService.create(request);
    }

}