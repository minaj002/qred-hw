package com.qred.service;

import com.qred.OrikoObjectMapper;
import com.qred.controller.ApplicationModel;
import com.qred.repository.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidationService {

    @Autowired
    private ValidationResource validationResource;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public ApplicationModel.Status validate(Application application) {

        ValidationResponse response = validationResource.validate(objectMapper.map(application, ApplicationModel.class));
        return response.getStatus();
    }
}
