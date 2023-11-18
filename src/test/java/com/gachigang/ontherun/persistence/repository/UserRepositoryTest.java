package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    static final String email = "test@example.com";

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFindUserByEmail() {
        User user = new User();
        user.setEmail(email);
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        assertNotNull(foundUser);
        assertEquals(user, foundUser.get());
    }

    @Test
    public void testFindAllByIdIn() {
        Long firstID = 1L;
        Long secondID = 2L;

        User firstUser = new User();
        User secondUser = new User();

        firstUser.setId(firstID);
        secondUser.setId(secondID);

        Set<Long> idSet = new HashSet<>();
        idSet.add(firstID);
        idSet.add(secondID);

        when(userRepository.findAllByIdIn(idSet)).thenReturn(Set.of(firstUser, secondUser));
        Set<User> foundUsers = userRepository.findAllByIdIn(idSet);

        assertEquals(2, foundUsers.size());
        assertTrue(foundUsers.contains(firstUser));
        assertTrue(foundUsers.contains(secondUser));
    }

    @Test
    public void testExistsByEmail_Positive() {
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
        when(userRepository.existsByEmail(email)).thenReturn(true);
        Boolean expected = userRepository.existsByEmail(email);
        assertEquals(true, expected);
    }

    @Test

    public void testExistsByEmail_Negative() {
        String fakeEmail = "fakeEmail@example.com";
        User user = new User();
        user.setEmail(fakeEmail);
        userRepository.save(user);
        Boolean expected = userRepository.existsByEmail(email);
        assertEquals(false, expected);
    }
}
