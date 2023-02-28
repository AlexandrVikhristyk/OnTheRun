package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.UpdateBusinessRequest;
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

    public Business getBusinessById(@NonNull final Long id){
        return businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Business with id " + id + " not found"));
    }

    public Business updateBusiness(UpdateBusinessRequest business, @NonNull Long id){
        Business businessById = getBusinessById(id);
        businessById.setName(business.getName());
        businessById.setCountry(business.getCountry());
        businessById.setCity(business.getCity());
        businessById.setOwners(business.getOwners());
        return businessRepository.save(businessById);
    }
}
