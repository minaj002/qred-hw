package com.qred.service;

import com.qred.controller.ApplicationModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ValidationResponse {

    private ApplicationModel.Status status;

}
