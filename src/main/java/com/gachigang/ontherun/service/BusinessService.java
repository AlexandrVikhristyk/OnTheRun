package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public List<Business> getAllBusiness() {

        return businessRepository.findAll();
    }
}
