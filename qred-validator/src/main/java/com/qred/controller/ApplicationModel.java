package com.qred.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ApplicationModel {

    public enum Status {
        APPLIED, VALIDATED, SENT_TO_VALIDATION,VALIDATION_SERVICE_FAILURE , VALIDATION_FAILED, CONFIRMED, REJECTED
    }
    private Long Id;
    @NotNull
    private BigDecimal amount;
    private String registrationNumber;
    private String email;
    private String phone;
    private BigDecimal yearly;
    private Integer term;
    private String name;
    private String type;
    private Status status;

}
