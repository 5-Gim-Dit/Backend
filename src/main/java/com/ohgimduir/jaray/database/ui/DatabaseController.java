package com.ohgimduir.jaray.database.ui;

import com.ohgimduir.jaray.common.response.CommonResponse;
import com.ohgimduir.jaray.database.application.command.CommandDatabaseService;
import com.ohgimduir.jaray.database.application.command.request.CreateDatabaseRequest;
import com.ohgimduir.jaray.database.application.query.QueryDatabaseService;
import com.ohgimduir.jaray.database.domain.entity.Database;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Database API")
@RestController
@RequestMapping(value = "/database")
@RequiredArgsConstructor
public class DatabaseController {

    private final CommandDatabaseService commandDatabaseService;
    private final QueryDatabaseService queryDatabaseService;

    @Operation(description = "Database(=Space) 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateDatabaseRequest request) {
        commandDatabaseService.create(request);
    }

    @Operation(description = "내 Database 조회")
    @GetMapping("/my")
    public CommonResponse<List<Database>> getMy() {
        return queryDatabaseService.getMy();
    }

}