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
public class BlackList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String registrationNumber;
    private LocalDateTime created;

}
