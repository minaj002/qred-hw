package com.qred.controller;

import com.qred.ObjectMapperConfiguration;
import com.qred.repository.Loan;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanToLoanModelMapper extends ObjectMapperConfiguration<Loan, LoanModel> {
    @Override
    public Class<Loan> getA() {
        return Loan.class;
    }

    @Override
    public Class<LoanModel> getB() {
        return LoanModel.class;
    }

    @Override
    protected void fieldMapping(ClassMapBuilder<Loan, LoanModel> builder) {

        Mapper<Loan, LoanModel> mapper = new CustomMapper<Loan, LoanModel>() {
            @Override
            public void mapAtoB(Loan loan, LoanModel loanModel, MappingContext context) {
                loanModel.setTotalPrincipal(loan.getPayments().stream().map(paymet-> paymet.getCommission()).reduce(BigDecimal.ZERO, BigDecimal::add));
            }

        };
        builder
                .customize(mapper)
                .byDefault();

    }


}

