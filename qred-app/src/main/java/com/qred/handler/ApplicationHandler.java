package com.qred.handler;

import com.qred.OrikoObjectMapper;
import com.qred.controller.ApplicationModel;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.time.LocalDateTime;

import static com.qred.controller.ApplicationModel.Status.APPLIED;


@Component
public class ApplicationHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public ApplicationModel handle(ApplicationModel applicationModel) {


        Application application = objectMapper.map(applicationModel, Application.class);
        if(application.getTerm() == null) {
            application.setTerm(6);
        }
        application.setStatus(APPLIED);
        application.setCreated(LocalDateTime.now());

        applicationRepository.save(application);

        return objectMapper.map(application, ApplicationModel.class);
    }




}
