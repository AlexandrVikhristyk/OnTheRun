package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.common.mapper.BusinessMapper;
import com.gachigang.ontherun.payload.business.BusinessDto;
import com.gachigang.ontherun.payload.business.request.BusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    private final Long VALID_BUSINESS_ID = 1L;
    private final Long INVALID_BUSINESS_ID = 7L;

    private static final Business BUSINESS = Business.builder()
            .id(1L)
            .name("Sample Business")
            .country("Sample Country")
            .city("Sample City")
            .owners(new HashSet<>())
            .departments(new HashSet<>())
            .build();

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private BusinessMapper businessMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private BusinessService businessService;

    @Test
    void testGetAllBusiness() {
        Page<Business> page = new PageImpl<>(Collections.emptyList());

        PageRequest pageRequest = PageRequest.of(0, 10);
        when(businessRepository.findAll(pageRequest)).thenReturn(page);

        List<Business> result = businessService.getAllBusiness(pageRequest);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetBusinessByIdExists() {
        when(businessRepository.findById(VALID_BUSINESS_ID)).thenReturn(Optional.of(BUSINESS));

        Business result = businessService.getBusinessById(VALID_BUSINESS_ID);

        assertNotNull(result);
        assertEquals(BUSINESS, result);
    }

    @Test
    void testGetBusinessByIdNotExists() {
        when(businessRepository.findById(INVALID_BUSINESS_ID)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> businessService.getBusinessById(INVALID_BUSINESS_ID));

    }

    @Test
    public void testUpdateBusiness() {
        Long businessId = 1L;
        BusinessDto businessDto = new BusinessDto(
                "SampleName",
                "SampleCountry",
                "SampleCity",
                Set.of(1L, 2L, 3L)
        );

        Business existingBusiness = new Business();
        when(businessRepository.findById(businessId)).thenReturn(java.util.Optional.of(existingBusiness));

        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();

        when(userService.getUserById(any(Long.class))).thenReturn(user1, user2);

        businessService.updateBusiness(businessDto, businessId);

        verify(userService, times(3)).getUserById(any(Long.class));
        verify(businessMapper, times(1)).updateBusiness(any(Business.class), eq(businessDto));
        verify(businessRepository, times(1)).save(any(Business.class));
    }

    @Test
    void testDeleteBusinessById_Positive() {
        final Long id = 1L;
        businessRepository.deleteBusinessById(id);
        verify(businessRepository, times(1)).deleteBusinessById(id);
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
    void testFindBusinessByOwners_Negative() {
        User user = new User();
        lenient().when(businessRepository.findBusinessByOwners(user)).thenReturn(Collections.emptyList());
        assertThrows(NullPointerException.class, () -> businessService.findBusinessByOwners(user));
    }

    @Test
    public void testCreateBusiness() {
        BusinessRequest businessRequest = new BusinessRequest();
        User user = new User();
        Business businessToSave = new Business();
        Business savedBusiness = new Business();

        when(businessMapper.businessRequestToBusiness(businessRequest)).thenReturn(businessToSave);
        when(businessRepository.save(businessToSave)).thenReturn(savedBusiness);

        Business result = businessService.createBusiness(businessRequest, user);

        assertNotNull(result);
        assertEquals(savedBusiness, result);

        verify(businessRepository, times(1)).save(businessToSave);
    }
}