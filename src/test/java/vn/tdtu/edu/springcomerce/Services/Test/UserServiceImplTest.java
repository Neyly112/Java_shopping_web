package vn.tdtu.edu.springcomerce.Services.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.tdtu.edu.springcomerce.Repository.UserRepo;
import vn.tdtu.edu.springcomerce.Services.Impl.UserServiceImpl;
import vn.tdtu.edu.springcomerce.models.Role;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.models.UserCheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveUser_SuccessfulSave() {
        // Arrange
        UserCheck userCheck = new UserCheck();
        userCheck.setUsername("testuser");
        userCheck.setFname("Test");
        userCheck.setLname("User");
        userCheck.setEmail("test@example.com");
        userCheck.setPassword("password123");

        User expectedUser = new User();
        expectedUser.setUsername("testuser");
        expectedUser.setFname("Test");
        expectedUser.setLname("User");
        expectedUser.setEmail("test@example.com");
        expectedUser.setPassword("encodedPassword");
        expectedUser.setRole(Role.USER);

        when(passwordEncoder.encode(userCheck.getPassword())).thenReturn("encodedPassword");

        // Act
        userService.saveUser(userCheck);

        // Assert
        verify(passwordEncoder, times(1)).encode(userCheck.getPassword());
        verify(userRepo, times(1)).save(argThat(user ->
                user.getUsername().equals("testuser") &&
                        user.getPassword().equals("encodedPassword") &&
                        user.getRole() == Role.USER
        ));
    }
    @Test
    void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "testuser";
        User mockUser = new User();
        mockUser.setUsername(username);
        when(userRepo.findByUsername(username)).thenReturn(mockUser);

        // Act
        UserDetails result = userService.loadUserByUsername(username);

        // Assert
        assertEquals(username, result.getUsername());
        verify(userRepo, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistentuser";
        when(userRepo.findByUsername(username)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(UsernameNotFoundException.class, () ->
                userService.loadUserByUsername(username)
        );
        assertEquals("User not found with username: nonexistentuser", exception.getMessage());
        verify(userRepo, times(1)).findByUsername(username);
    }

}
