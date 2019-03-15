package com.qred.service;

import com.qred.controller.ApplicationModel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class ValidationResourceFallbackFactory implements FallbackFactory<ValidationResource> {


    @Override
    public ValidationResource create(Throwable cause) {
        return new ValidationResource() {
            @Override
            public ValidationResponse validate(ApplicationModel application) {
                ValidationResponse response = new ValidationResponse();
                response.setStatus(ApplicationModel.Status.VALIDATION_SERVICE_FAILURE);
                return response;
            }
        };
    }
}

