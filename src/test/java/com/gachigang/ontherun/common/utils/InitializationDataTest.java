package com.gachigang.ontherun.common.utils;

import com.gachigang.ontherun.persistence.repository.RoleRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitializationDataTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private InitializationData initializationData;

    public void testRunDdlAutoCreate_Positive() {
    }
}