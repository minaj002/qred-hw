package com.qred.annotation;

import com.qred.repository.ApplicationRepository;
import com.qred.repository.BlackList;
import com.qred.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

@Component
public class BlackListValidator implements ConstraintValidator<BlackListCheck, String> {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private BlackListRepository blackListRepository;

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

        if(count>2) {
            BlackList newEntry = new BlackList();
            newEntry.setCreated(LocalDateTime.now());
            newEntry.setRegistrationNumber(value);
            return false;
        }
        return true;
    }
}

