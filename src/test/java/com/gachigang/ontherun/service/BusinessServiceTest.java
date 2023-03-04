package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
    void testFindBusinessByOwners_Positive() {
        Business business = Business.builder().
                id(1L)
                .name("testName").
                country("Ukraine").
                city("Kyiv").
                build();
        User user = User.builder().
                businesses(Collections.singleton(business)).
                build();
        when(businessRepository.findBusinessByOwners(user)).thenReturn(Collections.singletonList(business));
        List<Business> result = businessService.findBusinessByOwners(user);
        assertNotNull(result);
    }

    @Test
    void testFindBusinessByOwners_Negative(){
        User user = new User();
        lenient().when(businessRepository.findBusinessByOwners(user)).thenReturn(Collections.emptyList());
        assertThrows(NullPointerException.class, () ->businessService.findBusinessByOwners(user));
    }

    @Test
    void testCreateBusiness(){
        User user = new User();
        BusinessRequest businessRequest = BusinessRequest.builder()
                .name("TestName")
                .country("TestCountry")
                .city("TestCity")
                .build();
        Business business = Business.builder()
                .name(businessRequest.getName())
                .country(businessRequest.getCountry())
                .city(businessRequest.getCity())
                .build();
        user.setBusinesses(Collections.singleton(business));
        assertNotNull(business);
        assertNotNull(user);
        assertThat(Arrays.asList(business), hasItem(business));
    }
}