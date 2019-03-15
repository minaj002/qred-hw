package com.qred.handler;

import com.qred.OrikoObjectMapper;
import com.qred.controller.ApplicationModel;
import com.qred.infrastructure.ValidationException;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import com.qred.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.qred.controller.ApplicationModel.Status.*;


@Component
public class ValidationHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private ValidationService validationService;


    public ApplicationModel handle(Long id) {

        Application application = applicationRepository.findById(id).get();
        if(!(APPLIED.equals(application.getStatus())||VALIDATION_SERVICE_FAILURE.equals(application.getStatus()))) {
            throw new ValidationException(String.format("Application must be in status APPLIED or VALIDATION_SERVICE_FAILURE, but is in status %s", application.getStatus()));
        }
        application.setStatus(SENT_TO_VALIDATION);

        applicationRepository.save(application);
        ApplicationModel.Status result = validationService.validate(application);
        application.setStatus(result);
        applicationRepository.save(application);
        return objectMapper.map(application, ApplicationModel.class);
    }




}
