package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BusinessService {

    private final BusinessRepository businessRepository;


    public List<Business> getAllBusiness() {
        return businessRepository.findAll();
    }

    @Transactional
    public void deleteBusinessById(@NonNull final Long id) {
        businessRepository.deleteBusinessById(id);
    }

    public List<Business> findBusinessByOwners(User user) {
        if (user.getBusinesses().isEmpty()) {
            throw new RuntimeException("Not found Business for this user: " + user);
        }
        return businessRepository.findBusinessByOwners(user);
    }
}
