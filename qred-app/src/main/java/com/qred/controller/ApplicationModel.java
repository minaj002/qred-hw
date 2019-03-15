package com.qred.controller;

import com.qred.annotation.BlackListCheck;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ApplicationModel {

    public enum Status {
        APPLIED, VALIDATED, SENT_TO_VALIDATION, VALIDATION_FAILED,VALIDATION_SERVICE_FAILURE ,CONFIRMED, REJECTED, BLACKLISTED
    }
    private Long Id;
    @NotNull
    private BigDecimal amount;
    @NotEmpty
    @BlackListCheck
    private String registrationNumber;
    @Email
    private String email;
    @NotEmpty
    private String phone;
    @NotNull
    private BigDecimal yearly;
    @Min(6)@Max(12)
    private Integer term;
    private String name;
    private String type;
    private Status status;

}
