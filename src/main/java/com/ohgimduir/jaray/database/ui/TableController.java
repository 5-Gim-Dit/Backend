package com.ohgimduir.jaray.database.ui;

import com.ohgimduir.jaray.common.response.CommonResponse;
import com.ohgimduir.jaray.database.application.command.CommandTableService;
import com.ohgimduir.jaray.database.application.command.request.CreateTableRequest;
import com.ohgimduir.jaray.database.application.query.QueryTableService;
import com.ohgimduir.jaray.database.application.query.response.TableResponse;
import com.ohgimduir.jaray.database.domain.entity.Table;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Table API")
@RestController
@RequestMapping(value = "/table")
@RequiredArgsConstructor
public class TableController {

    private final CommandTableService commandTableService;
    private final QueryTableService queryTableService;

    @Operation(description = "Table 생성")
    @PostMapping("/{databaseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Long databaseId, @RequestBody CreateTableRequest request) {
        commandTableService.create(databaseId, request);
    }

    @Operation(description = "Table 조회 by TableId)")
    @GetMapping("/{tableId}")
    public TableResponse getById(@PathVariable long tableId) {
        return queryTableService.getById(tableId);
    }

    @Operation(description = "Table 조회 by Database")
    @GetMapping("/by/{databaseId}")
    public CommonResponse<List<Table>> getByDatabase(@PathVariable long databaseId) {
        return queryTableService.getByDatabase(databaseId);
    }

}