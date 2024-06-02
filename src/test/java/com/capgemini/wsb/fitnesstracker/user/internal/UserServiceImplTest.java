package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllUsers_returnsListOfUsers() {
        List<User> users = Arrays.asList(
                createUser(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com"),
                createUser(2L, "Jane", "Smith", LocalDate.of(1985, 5, 5), "jane.smith@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUser_returnsUser() {
        User user = createUser(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void createUser_savesAndReturnsUser() {
        User user = createUser(null, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_deletesUser() {
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void updateUser_updatesAndReturnsUser() {
        User user = createUser(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser(1L, user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findByEmailContainingIgnoreCase_returnsListOfUsers() {
        List<User> users = Arrays.asList(
                createUser(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com"),
                createUser(2L, "Jane", "Smith", LocalDate.of(1985, 5, 5), "jane.smith@example.com")
        );
        when(userRepository.findByEmailContainingIgnoreCase(anyString())).thenReturn(users);

        List<User> result = userService.findByEmailContainingIgnoreCase("john");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findByEmailContainingIgnoreCase(anyString());
    }

    @Test
    void findUsersOlderThan_returnsListOfUsers() {
        List<User> users = Arrays.asList(
                createUser(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com"),
                createUser(2L, "Jane", "Smith", LocalDate.of(1985, 5, 5), "jane.smith@example.com")
        );
        when(userRepository.findByBirthdateBefore(any(LocalDate.class))).thenReturn(users);

        List<User> result = userService.findUsersOlderThan(30);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findByBirthdateBefore(any(LocalDate.class));
    }

    private User createUser(Long id, String firstName, String lastName, LocalDate birthdate, String email) {
        User user = new User(firstName, lastName, birthdate, email);
        setId(user, id);
        return user;
    }

    private void setId(User user, Long id) {
        try {
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
