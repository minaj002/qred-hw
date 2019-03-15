package com.qred.handler;

import com.qred.OrikoObjectMapper;
import com.qred.controller.ApplicationModel;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.qred.controller.ApplicationModel.Status.REJECTED;
import static com.qred.controller.ApplicationModel.Status.VALIDATED;


@Component
public class RejectionHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public ApplicationModel handle(Long id) {

        Application application = applicationRepository.findById(id).get();
        application.setStatus(REJECTED);

        applicationRepository.save(application);

        return objectMapper.map(application, ApplicationModel.class);
    }




}
