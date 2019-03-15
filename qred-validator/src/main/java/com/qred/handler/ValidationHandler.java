package com.qred.handler;

import com.qred.controller.ApplicationModel;
import com.qred.controller.ValidationResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.qred.controller.ApplicationModel.Status.VALIDATED;
import static com.qred.controller.ApplicationModel.Status.VALIDATION_FAILED;


@Component
public class ValidationHandler {

    public ValidationResponse handle(ApplicationModel application) {

        BigDecimal principal = application.getAmount().divide(BigDecimal.valueOf(application.getTerm()), 2, RoundingMode.CEILING);
        BigDecimal commission = application.getAmount().multiply(BigDecimal.valueOf(.03));

        BigDecimal monthlyExpense = principal.add(commission);

        BigDecimal maxAllowedLimit = application.getYearly().divide(BigDecimal.valueOf(12), 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(.3));

        if(monthlyExpense.compareTo(maxAllowedLimit)<0) {
            return ValidationResponse.builder().status(VALIDATED).build();
        } else {
            return ValidationResponse.builder().status(VALIDATION_FAILED).build();

        }

    }




}
