package com.example.tasktracker;

import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.user.UserRepository;
import com.example.tasktracker.rest.dto.SaveUserDto;
import com.example.tasktracker.rest.dto.UserDto;
import com.example.tasktracker.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        user1.setId(1);
        user1.setLogin("john.doe@example.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");

        user2.setId(2);
        user2.setLogin("jane.doe@example.com");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");

        List<User> users = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(user1.getFirstName(), result.get(0).getFirstName());
        assertEquals(user2.getFirstName(), result.get(1).getFirstName());
    }

    @Test
    public void testCreateUser() {
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setFirstName("John");
        saveUserDto.setLastName("Doe");
        saveUserDto.setEmail("john.doe@example.com");
        saveUserDto.setLogin("john.doe@example.com");
        saveUserDto.setPassword("password");

        userService.createUser(saveUserDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindUserById()  {
        User user = new User();
        user.setId(1);
        user.setLogin("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDto result = userService.findUserById(1);

        assertNotNull(result);
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getLogin(), result.getLogin());
    }

    @Test
    public void testUpdateUser()  {
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setFirstName("Jane");
        saveUserDto.setLastName("Doe");
        saveUserDto.setEmail("jane.doe@example.com");
        saveUserDto.setLogin("jane.doe@example.com");
        saveUserDto.setPassword("newpassword");

        User userToUpdate = userMapper.saveUserDtoToUser(saveUserDto);
        userToUpdate.setId(1);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");
        existingUser.setEmail("john.doe@example.com");
        existingUser.setLogin("john.doe@example.com");
        existingUser.setHashPassword(passwordEncoder.encode("password"));

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        userService.updateUser(1, saveUserDto);
        when(userRepository.findById(1)).thenReturn(Optional.of(userToUpdate));

        UserDto updatedUser = userService.findUserById(1);

        assertEquals(userToUpdate.getFirstName(), updatedUser.getFirstName());
        assertEquals(userToUpdate.getLastName(), updatedUser.getLastName());
        assertEquals(userToUpdate.getEmail(), updatedUser.getEmail());
        assertEquals(userToUpdate.getLogin(), updatedUser.getLogin());

        verify(userRepository, times(1)).save(userToUpdate);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }
}
