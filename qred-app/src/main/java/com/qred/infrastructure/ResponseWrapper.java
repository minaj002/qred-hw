package com.qred.infrastructure;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ResponseWrapper<T>{

    private String message;
    private List<ValidationError> errors;

}

