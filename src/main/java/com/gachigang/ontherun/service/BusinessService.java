package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class BusinessService {

    private final BusinessRepository businessRepository;


    public List<Business> getAllBusiness() {
        return businessRepository.findAll();
    }

    public List<Business> findBusinessByOwners(User user) {
        if (user.getBusinesses().isEmpty()) {
            throw new RuntimeException("Not found Business for this user: " + user);
        }
        return businessRepository.findBusinessByOwners(user);
    }
    public Business createBusiness(BusinessRequest businessRequest, User user){
        Business business = Business.builder()
                .name(businessRequest.getName())
                .country(businessRequest.getCountry())
                .city(businessRequest.getCity())
                .build();
        user = User.builder()
                .businesses(Collections.singleton(business))
                .build();
        business.setOwners(Collections.singleton(user));
        return businessRepository.save(business);
    }
}
