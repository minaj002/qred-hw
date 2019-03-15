package com.qred.controller;

import com.qred.handler.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ApplicationController {

    @Autowired
    private ValidationHandler applicationHandler;

    @PostMapping("/api/validate")
    @ApiOperation(value = "validate application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity validate(@RequestBody @Valid ApplicationModel application) {
        return ResponseEntity.ok(applicationHandler.handle(application));
    }

}
