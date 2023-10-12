package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.payload.business.request.UpdateBusinessRequest;
import com.gachigang.ontherun.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BusinessControllerTest {
    @Mock
    private BusinessService businessService;

    @InjectMocks
    private BusinessController businessController;

    @Test
    void testGetAllBusiness() {
        int page = 0;
        int size = 10;
        businessController.getAllBusiness(0,10);
        verify(businessService, times(1)).getAllBusiness(PageRequest.of(page, size));
    }

    @Test
    void testUpdateBusiness() {
        Long businessId = 1L;
        UpdateBusinessRequest testBusiness = new UpdateBusinessRequest();
        businessController.updateBusiness(testBusiness, businessId);
        verify(businessService, times(1)).updateBusiness(testBusiness, businessId);
    }

    @Test
    void testFindBusinessByOwners() {
        User user = new User();
        businessController.findBusinessByOwners(user);
        verify(businessService, times(1)).findBusinessByOwners(user);
    }
}