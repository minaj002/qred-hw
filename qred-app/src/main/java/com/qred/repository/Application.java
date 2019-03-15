package com.qred.repository;

import com.qred.controller.ApplicationModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private String registrationNumber;
    private String email;
    private String phone;
    private BigDecimal yearly;
    private Integer term;
    private String name;
    private String type;
    private ApplicationModel.Status status;
    private LocalDateTime created;

}
