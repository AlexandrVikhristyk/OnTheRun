package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        businessController.getAllBusiness();
        verify(businessService, times(1)).getAllBusiness();
    }
}