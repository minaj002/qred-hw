package com.qred.controller;

import com.qred.ObjectMapperConfiguration;
import com.qred.repository.Application;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToApplicationModelMapper extends ObjectMapperConfiguration<Application, ApplicationModel> {
    @Override
    public Class<Application> getA() {
        return Application.class;
    }

    @Override
    public Class<ApplicationModel> getB() {
        return ApplicationModel.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<Application, ApplicationModel> builder) {

        builder
                .byDefault();

    }

}

