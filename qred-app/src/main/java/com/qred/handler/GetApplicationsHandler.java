package com.qred.handler;

import com.qred.OrikoObjectMapper;
import com.qred.controller.ApplicationModel;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.qred.controller.ApplicationModel.Status.APPLIED;


@Component
public class GetApplicationsHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public List<ApplicationModel> handle() {

        List<Application> applications = StreamSupport.stream(applicationRepository.findAll().spliterator(), false).collect(Collectors.<Application>toList());

        return objectMapper.map(applications, ApplicationModel.class);
    }




}
