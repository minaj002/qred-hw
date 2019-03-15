package com.qred.handler;

import com.qred.OrikoObjectMapper;
import com.qred.controller.LoanModel;
import com.qred.infrastructure.ValidationException;
import com.qred.repository.Application;
import com.qred.repository.ApplicationRepository;
import com.qred.repository.Loan;
import com.qred.repository.LoanRepository;
import com.qred.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.qred.controller.ApplicationModel.Status.CONFIRMED;
import static com.qred.controller.ApplicationModel.Status.VALIDATED;


@Component
public class ConfirmationHandler {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private LoanService loanService;

    public LoanModel handle(Long id) {

        Application application = applicationRepository.findById(id).get();
        if(!VALIDATED.equals(application.getStatus())) {
            throw new ValidationException(String.format("Application must be in status VALIDATED, but is in status %s", application.getStatus()));
        }
        application.setStatus(CONFIRMED);
        applicationRepository.save(application);
        Loan loan = loanService.createSchedule(application);
        loan.setApplication(application);
        loanRepository.save(loan);
        return objectMapper.map(loan, LoanModel.class);
    }

}
