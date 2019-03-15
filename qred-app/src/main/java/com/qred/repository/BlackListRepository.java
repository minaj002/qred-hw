package com.qred.repository;

import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, Long> {

    BlackList findByRegistrationNumber(String registrationNumber);

}
