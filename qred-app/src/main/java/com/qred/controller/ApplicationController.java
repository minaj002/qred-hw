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
    private ApplicationHandler applicationHandler;
    @Autowired
    private ValidationHandler validationHandler;
    @Autowired
    private RejectionHandler rejectionHandler;
    @Autowired
    private ConfirmationHandler confirmationHandler;
    @Autowired
    private GetApplicationsHandler getApplicationsHandler;


    @GetMapping("/api/application")
    @ApiOperation(value = "get all applications")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity getApplications() {
        return ResponseEntity.ok(getApplicationsHandler.handle());
    }

    @PostMapping("/api/application/apply")
    @ApiOperation(value = "apply for new loan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity apply(@RequestBody @Valid ApplicationModel application) {
        return ResponseEntity.ok(applicationHandler.handle(application));
    }

    @PutMapping("/api/application/{id}/validate")
    @ApiOperation(value = "validate application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity validate(@PathVariable Long id) {
        return ResponseEntity.ok(validationHandler.handle(id));
    }

    @PutMapping("/api/application/{id}/reject")
    @ApiOperation(value = "reject application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity reject(@PathVariable Long id) {
        return ResponseEntity.ok(rejectionHandler.handle(id));
    }

    @PutMapping("/api/application/{id}/confirm")
    @ApiOperation(value = "confirm application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity confirm(@PathVariable Long id) {
        return ResponseEntity.ok(confirmationHandler.handle(id));
    }



}
