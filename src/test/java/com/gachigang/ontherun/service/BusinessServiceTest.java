package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.BusinessRequest;
import com.gachigang.ontherun.payload.user.request.UpdateBusinessRequest;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 7L;

    @Mock
    private BusinessRepository businessRepository;
    @Mock
    private UserRepository userRepository;

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

    @Test
    void testUpdateBusinessWithoutChangedOwners(){
        UpdateBusinessRequest updateBusinessRequest = new UpdateBusinessRequest(
                "Boba",
                "Ukraine",
                "Kyiv",
                new HashSet<>(Arrays.asList(2L, 3L))
        );
        User owner1 = User.builder().id(2L).build();
        User owner2 = User.builder().id(3L).build();
        Business business = Business.builder()
                .id(VALID_ID)
                .city("Kyiv")
                .country("Ukraine")
                .owners(new HashSet<>(Arrays.asList(owner1, owner2)))
                .build();

        when(businessRepository.findById(VALID_ID)).thenReturn(Optional.of(business));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        Business result = businessService.updateBusiness(updateBusinessRequest, VALID_ID);

        verify(businessRepository, times(1)).findById(VALID_ID);
        verify(userRepository, times(0)).findAllByIdIn(updateBusinessRequest.getOwners());
        verify(businessRepository, times(1)).save(business);
        assertEquals(updateBusinessRequest.getName(), result.getName());
        assertEquals(updateBusinessRequest.getCountry(), result.getCountry());
        assertEquals(updateBusinessRequest.getCity(), result.getCity());
        assertEquals(updateBusinessRequest.getOwners(),
                result.getOwners()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet()));
    }

    @Test
    void testUpdateBusinessWithChangedOwners(){
        UpdateBusinessRequest updateBusinessRequest = new UpdateBusinessRequest(
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
                .id(VALID_ID)
                .city("Kyiv")
                .country("Ukraine")
                .owners(new HashSet<>(Arrays.asList(owner1, owner2, owner3)))
                .build();

        when(businessRepository.findById(VALID_ID)).thenReturn(Optional.of(business));
        when(userRepository.findAllByIdIn(updateBusinessRequest.getOwners())).thenReturn(new HashSet<>(Arrays.asList(owner1, owner2)));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        Business result = businessService.updateBusiness(updateBusinessRequest, VALID_ID);

        verify(businessRepository, times(1)).findById(VALID_ID);
        verify(userRepository, times(1)).findAllByIdIn(updateBusinessRequest.getOwners());
        verify(businessRepository, times(1)).save(business);
        assertEquals(updateBusinessRequest.getName(), result.getName());
        assertEquals(updateBusinessRequest.getCountry(), result.getCountry());
        assertEquals(updateBusinessRequest.getCity(), result.getCity());
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
    void testFindBusinessByOwners_Negative(){
        User user = new User();
        lenient().when(businessRepository.findBusinessByOwners(user)).thenReturn(Collections.emptyList());
        assertThrows(NullPointerException.class, () ->businessService.findBusinessByOwners(user));
    }

    @Test
    void testCreateBusiness(){
        User user = new User();
        final BusinessRequest businessRequest = BusinessRequest.builder()
                .name("TestName")
                .country("TestCountry")
                .city("TestCity")
                .build();
        final Business business = Business.builder()
                .name(businessRequest.getName())
                .country(businessRequest.getCountry())
                .city(businessRequest.getCity())
                .owners(Collections.singleton(user))
                .build();

        when(businessRepository.save(business)).thenReturn(business);
        Business result = businessService.createBusiness(businessRequest, user);


        assertNotNull(result);
        assertEquals(businessRequest.getName(), result.getName());
        assertEquals(businessRequest.getCountry(), result.getCountry());
        assertEquals(businessRequest.getCity(), result.getCity());
    }
}