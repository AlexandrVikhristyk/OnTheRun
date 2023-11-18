package com.gachigang.ontherun.service;

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
import java.util.stream.Collectors;

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
        assertThrows(IllegalArgumentException.class, () -> businessService.getBusinessById(INVALID_BUSINESS_ID));
    }

    @Test
    void testUpdateBusinessWithoutChangedOwners() {
        BusinessDto businessDto = new BusinessDto(
                "Boba",
                "Ukraine",
                "Kyiv",
                new HashSet<>(Arrays.asList(2L, 3L))
        );
        User owner1 = User.builder().id(2L).build();
        User owner2 = User.builder().id(3L).build();
        Business business = Business.builder()
                .id(VALID_BUSINESS_ID)
                .city("Kyiv")
                .country("Ukraine")
                .owners(new HashSet<>(Arrays.asList(owner1, owner2)))
                .build();

        when(businessRepository.findById(VALID_BUSINESS_ID)).thenReturn(Optional.of(business));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        Business result = businessService.updateBusiness(businessDto, VALID_BUSINESS_ID);

        verify(businessRepository, times(1)).findById(VALID_BUSINESS_ID);
        verify(userRepository, times(0)).findAllByIdIn(businessDto.getOwners());
        verify(businessRepository, times(1)).save(business);
        assertEquals(businessDto.getName(), result.getName());
        assertEquals(businessDto.getCountry(), result.getCountry());
        assertEquals(businessDto.getCity(), result.getCity());
        assertEquals(businessDto.getOwners(),
                result.getOwners()
                        .stream()
                        .map(User::getId)
                        .collect(Collectors.toSet()));
    }

    @Test
    void testUpdateBusinessWithChangedOwners() {
        BusinessDto businessDto = new BusinessDto(
                "Boba",
                "Ukraine",
                "Kyiv",
                new HashSet<>(Arrays.asList(2L, 3L))
        );

        User owner1 = new User();
        User owner2 = new User();
        User owner3 = new User();
        owner1.setId(2L);
        owner2.setId(3L);
        owner3.setId(4L);
        Business business = Business.builder()
                .id(VALID_BUSINESS_ID)
                .city("Kyiv")
                .country("Ukraine")
                .owners(new HashSet<>(Arrays.asList(owner1, owner2, owner3)))
                .build();

        when(businessRepository.findById(VALID_BUSINESS_ID)).thenReturn(Optional.of(business));
        when(userRepository.findAllByIdIn(businessDto.getOwners())).thenReturn(new HashSet<>(Arrays.asList(owner1, owner2)));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        Business result = businessService.updateBusiness(businessDto, VALID_BUSINESS_ID);

        verify(businessRepository, times(1)).findById(VALID_BUSINESS_ID);
        verify(userRepository, times(1)).findAllByIdIn(businessDto.getOwners());
        verify(businessRepository, times(1)).save(business);
        assertEquals(businessDto.getName(), result.getName());
        assertEquals(businessDto.getCountry(), result.getCountry());
        assertEquals(businessDto.getCity(), result.getCity());
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
        // Arrange
        BusinessRequest businessRequest = new BusinessRequest();
        User user = new User(); // Mock or create a real User object as needed
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