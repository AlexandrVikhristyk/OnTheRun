package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.mapper.BusinessMapper;
import com.gachigang.ontherun.payload.business.BusinessDto;
import com.gachigang.ontherun.payload.business.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    @Transactional(readOnly = true)
    public List<Business> getAllBusiness(PageRequest pageRequest) {
        Page<Business> page = businessRepository.findAll(pageRequest);
        return page.getContent();
    }

    @Transactional(readOnly = true)
    public Business getBusinessById(@NonNull final Long id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Business with id " + id + " not found"));
    }

    @Transactional
    public Business updateBusiness(@NonNull final BusinessDto businessDto,
                                   @NonNull final Long id) {
        Business business = getBusinessById(id);

        businessMapper.updateBusiness(business, businessDto);
        return businessRepository.save(business);
    }

    @Transactional
    public Business deleteBusinessById(@NonNull final Long id) {
        Business business = getBusinessById(id);
        businessRepository.deleteBusinessById(id);
        return business;
    }

    @Transactional(readOnly = true)
    public List<Business> findBusinessByOwners(User user) {
        if (user.getBusinesses().isEmpty()) {
            throw new RuntimeException("Not found Business for this user: " + user);
        }
        return businessRepository.findBusinessByOwners(user);
    }

    @Transactional
    public Business createBusiness(BusinessRequest businessRequest, User user) {
        Business business = businessMapper.businessRequestToBusiness(businessRequest);
        business.setOwners(Collections.singleton(user));
        return businessRepository.save(business);
    }
}