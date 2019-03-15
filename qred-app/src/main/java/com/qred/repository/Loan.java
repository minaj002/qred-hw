package com.qred.repository;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private LocalDate created;
    private BigDecimal rate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;
    @OneToOne
    private Application application;


}
