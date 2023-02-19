package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final static User USER = User.builder()
            .id(1L)
            .surname("testsurname")
            .lastname("testlastname")
            .login("testLogin")
            .email("test@email.com")
            .dateOfBirth(LocalDate.of(2002, 12, 2))
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
    public void testSaveUser_successfully() {
        when(userRepository.save(USER))
                .thenReturn(USER);

        final User createdUser = userService.save(USER);

        verify(userRepository, times(1)).save(USER);
        assertNotNull(createdUser);
    }


    @Test
    public void testGetUserByEmail_isCorrect() {
        when(userRepository.findUserByEmail(EMAIL))
                .thenReturn(Optional.ofNullable(USER));

        final User actualUser = userService.getByEmail(EMAIL);

        verify(userRepository, times(1)).findUserByEmail(EMAIL);
        assertEquals(USER, actualUser);
    }
}
