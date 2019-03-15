package com.qred.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {

    private ApplicationModel.Status status;

}
