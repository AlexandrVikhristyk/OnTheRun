package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.mapper.BusinessMapper;
import com.gachigang.ontherun.payload.business.BusinessDto;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessControllerTest {

    @Mock
    private BusinessService businessService;

    @Mock
    private BusinessMapper businessMapper;

    @InjectMocks
    private BusinessController businessController;

    @Test
    void testGetAllBusiness() {
        int page = 0;
        int size = 10;
        businessController.getAllBusiness(0, 10);
        verify(businessService, times(1)).getAllBusiness(PageRequest.of(page, size));
    }

    @Test
    void testUpdateBusiness() {
        Long businessId = 1L;
        BusinessDto testBusinessDto = new BusinessDto("TestName", "TestCountry", "TestCity", new HashSet<>());
        Business testBusinessEntity = new Business();

        when(businessService.updateBusiness(testBusinessDto, businessId)).thenReturn(testBusinessEntity);
        when(businessMapper.businessToBusinessDto(testBusinessEntity)).thenReturn(testBusinessDto);

        ResponseEntity<BusinessDto> response = businessController.updateBusiness(testBusinessDto, businessId);

        verify(businessService, times(1)).updateBusiness(testBusinessDto, businessId);
        verify(businessMapper, times(1)).businessToBusinessDto(testBusinessEntity);
        verify(businessService, times(1)).updateBusiness(testBusinessDto, businessId);
        verify(businessMapper, times(1)).businessToBusinessDto(testBusinessEntity);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testBusinessDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestName", response.getBody().getName());
        assertEquals("TestCountry", response.getBody().getCountry());
        assertNotNull(response);
    }

    @Test
    void testFindBusinessByOwners() {
        User user = new User();
        businessController.findBusinessByOwners(user);
        verify(businessService, times(1)).findBusinessByOwners(user);
    }
}