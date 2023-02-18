package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final static User USER = User.builder()
            .id(1L)
            .surname("testsurname")
            .lastname("testlastname")
            .login("testLogin")
            .email("test@email.com")
            .dateOfBirth("31/12/2002")
            .country("Ukraine")
            .city("Kyiv")
            .password("testpassword")
            .build();

    private final static String EMAIL = "test@email.com";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser_successful() {
        when(userRepository.save(USER)).thenReturn(USER);

        final User createdUser = userService.save(USER);

        verify(userRepository, times(1)).save(USER);
        assertNotNull(createdUser);
    }

    @Test
    public void testGetUserByEmail_Positive() {
        when(userRepository.findUserByEmail(EMAIL)).thenReturn(Optional.ofNullable(USER));

        final User actualUser = userService.getByEmail(EMAIL);

        verify(userRepository, times(1)).findUserByEmail(EMAIL);
        assertEquals(USER, actualUser);
    }

    @Test
    public void testGetUserByEmail_Negative() {
        when(userRepository.findUserByEmail(any())).thenThrow(new UsernameNotFoundException("User by this email not found"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getByEmail(any()));
    }
}
