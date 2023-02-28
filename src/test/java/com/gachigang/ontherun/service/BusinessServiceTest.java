package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 2L;

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
    void testGetBusinessByIdExists(){
        Business business = Business.builder()
                .id(VALID_ID)
                .city("Kyiv")
                .country("Ukraine")
                .build();
        when(businessRepository.findById(VALID_ID)).thenReturn(Optional.of(business));
        Business result = businessService.getBusinessById(VALID_ID);
        assertNotNull(result);
        assertEquals(business.getId(), result.getId());
        assertEquals(business.getCity(), result.getCity());
        assertEquals(business.getCountry(), result.getCountry());
    }

    @Test
    void testGetBusinessByIdNotExists(){
        when(businessRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> businessService.getBusinessById(INVALID_ID));
    }
}