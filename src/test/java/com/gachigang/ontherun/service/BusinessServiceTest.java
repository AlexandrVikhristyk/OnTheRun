package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {
    @Mock
    private BusinessRepository businessRepository;

    @InjectMocks
    private BusinessService businessService;

    @Test
    void testGetAllBusiness() {
        businessService.getAllBusiness();
        verify(businessRepository, times(1)).findAll();
    }

    @Test
    void testGetBusinessById(){
        businessService.getBusinessById(1L);
        verify(businessRepository, times(1)).getById(1L);
    }
}