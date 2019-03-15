package com.qred.annotation;

import com.qred.controller.ApplicationModel;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import com.qred.repository.BlackList;
import com.qred.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.List;

import static com.qred.controller.ApplicationModel.Status.APPLIED;
import static com.qred.controller.ApplicationModel.Status.BLACKLISTED;

@Component
public class BlackListValidator implements ConstraintValidator<BlackListCheck, String> {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private BlackListRepository blackListRepository;
    @Value("${blackList.count}")
    private Integer blackListCount;

    @Override
    public void initialize(BlackListCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        BlackList blackList = blackListRepository.findByRegistrationNumber(value);
        if(blackList!=null) {
            return false;
        }
        Integer count = applicationRepository.countByRegistrationNumberAndCreatedGreaterThan(value, LocalDateTime.now().minusMinutes(5));

        if(count>=blackListCount) {
            BlackList newEntry = new BlackList();
            newEntry.setCreated(LocalDateTime.now());
            newEntry.setRegistrationNumber(value);

            List<Application> applications = applicationRepository.findByRegistrationNumberAndStatus(value, APPLIED);

            applications.forEach(application -> application.setStatus(BLACKLISTED));
            applicationRepository.saveAll(applications);

            return false;
        }
        return true;
    }
}

