package com.qred.repository;

import com.qred.controller.ApplicationModel;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    List<Application> findByRegistrationNumberAndStatus(String registrationNumber, ApplicationModel.Status status);
    Integer countByRegistrationNumberAndCreatedGreaterThan(String registrationNumber, LocalDateTime created);

}
