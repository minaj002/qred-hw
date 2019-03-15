package com.qred.controller;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class LoanModel {

    private Long id;
    private BigDecimal amount;
    private Integer term;
    private LocalDate created;
    private BigDecimal rate;
    private List<PaymentModel> payments;
    private BigDecimal totalPrincipal;

}
