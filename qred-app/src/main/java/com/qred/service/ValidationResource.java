package com.qred.service;

import com.qred.controller.ApplicationModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "validation-resource",
        url = "${val.url}",
        fallbackFactory = ValidationResourceFallbackFactory.class
)
public interface ValidationResource {
    @RequestMapping(method = RequestMethod.POST, value = "/api/validate")
    ValidationResponse validate(@RequestBody ApplicationModel application) ;

}
