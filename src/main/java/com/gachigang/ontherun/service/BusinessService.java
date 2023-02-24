package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public List<Business> getAllBusiness() {

        return businessRepository.findAll();
    }

    public Business getBusinessById(@NonNull Long id){
        return businessRepository.getById(id);
    }

    public Business updateBusiness(Business business, @NonNull Long id){
        Business businessById = businessRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Business with id " + id + "not found"));
        getBusinessById(id).setName(business.getName());
        getBusinessById(id).setCountry(business.getCountry());
        getBusinessById(id).setCity(business.getCity());
        businessRepository.save(businessById);
        return businessById;
    }
}
