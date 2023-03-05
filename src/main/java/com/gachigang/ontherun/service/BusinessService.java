package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.UpdateBusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public List<Business> getAllBusiness() {

        return businessRepository.findAll();
    }

    public Business getBusinessById(@NonNull final Long id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Business with id " + id + " not found"));
    }

    public Business updateBusiness(UpdateBusinessRequest businessRequest, @NonNull Long id) {
        Business businessById = getBusinessById(id);
        businessById.setName(businessRequest.getName());
        businessById.setCountry(businessRequest.getCountry());
        businessById.setCity(businessRequest.getCity());
        Set<Long> oldOwnersId = businessById.getOwners().stream().map(User::getId).collect(Collectors.toSet());
        if (businessRequest.getOwners().equals(oldOwnersId)) return businessRepository.save(businessById);
        businessById.getOwners().removeAll(businessById.getOwners());
        businessById.getOwners().addAll(userRepository.findAllByIdIn(businessRequest.getOwners()));
        return businessRepository.save(businessById);
    }
}
