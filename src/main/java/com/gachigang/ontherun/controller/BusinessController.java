package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessRepository businessRepository;

    @GetMapping
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
    }

    @GetMapping("/owner")
    public List<Business> findBusinessByOwners(@AuthenticationPrincipal User user)  {
        return businessService.findBusinessByOwners(user);
    }
}

