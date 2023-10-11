package com.ohgimduir.jaray.db.ui;

import com.ohgimduir.jaray.db.application.command.CommandDatabaseService;
import com.ohgimduir.jaray.db.application.command.dto.request.CreateDatabaseRequest;
import com.ohgimduir.jaray.db.application.query.QueryDataBaseService;
import com.ohgimduir.jaray.db.domain.entity.DataBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/database")
@RequiredArgsConstructor
public class DataBaseController {

    private final CommandDatabaseService commandDatabaseService;
    private final QueryDataBaseService queryDataBaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CreateDatabaseRequest request) {
        return commandDatabaseService.create(request);
    }

    @GetMapping("/my")
    public List<DataBase> getMy() {
        return queryDataBaseService.getMy();
    }

}