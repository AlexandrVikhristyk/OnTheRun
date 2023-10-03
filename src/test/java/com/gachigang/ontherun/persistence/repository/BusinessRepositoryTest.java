package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class BusinessRepositoryTest {

    @Mock
    private BusinessRepository businessRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteBusinessById() {
        Long businessId = 1L;
        businessRepository.deleteBusinessById(businessId);
        Mockito.verify(businessRepository).deleteBusinessById(businessId);
    }

    @Test
    public void testFindBusinessByOwners() {
        User user = new User();
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business());
        businesses.add(new Business());
        Mockito.when(businessRepository.findBusinessByOwners(user)).thenReturn(businesses);
        List<Business> foundBusinesses = businessRepository.findBusinessByOwners(user);
        assertEquals(2, foundBusinesses.size());
    }

    @Test
    public void testFindBusinessByOwners_EmptyList() {
        User user = new User();
        Mockito.when(businessRepository.findBusinessByOwners(user)).thenReturn(new ArrayList<>());
        List<Business> foundBusinesses = businessRepository.findBusinessByOwners(user);
        assertTrue(foundBusinesses.isEmpty());
    }

    @Test
    public void testFindBusinessByOwners_NullList() {
        User user = new User();
        Mockito.when(businessRepository.findBusinessByOwners(user)).thenReturn(null);
        List<Business> foundBusinesses = businessRepository.findBusinessByOwners(user);
            assertNull(foundBusinesses);
    }
}

