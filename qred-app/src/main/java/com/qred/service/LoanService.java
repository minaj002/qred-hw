package com.qred.service;

import com.qred.repository.Application;
import com.qred.repository.Loan;
import com.qred.repository.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class LoanService {

    public Loan createSchedule(Application application) {

        Loan loan = new Loan();
        loan.setAmount(application.getAmount());
        loan.setCreated(LocalDate.now());
        loan.setTerm(application.getTerm());
        loan.setRate(BigDecimal.valueOf(.03));

        BigDecimal principal = loan.getAmount().divide(BigDecimal.valueOf(loan.getTerm()), 2, RoundingMode.CEILING);
        BigDecimal commission = loan.getAmount().multiply(loan.getRate());
        List<Payment> payments = new ArrayList();

        for(int paymentNumer = 1; paymentNumer<=application.getTerm(); paymentNumer++ ) {
            Payment payment = new Payment();
            payment.setDate(loan.getCreated().plusMonths(paymentNumer));
            payment.setPaymentNumber(paymentNumer);
            payment.setCommission(commission);
            payment.setPrincipal(principal);
            payments.add(payment);
        }
        loan.setPayments(payments);
        return loan;
    }
}
