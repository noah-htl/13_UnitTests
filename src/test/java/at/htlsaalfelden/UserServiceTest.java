package at.htlsaalfelden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService();
    }

    @Test
    void testRegisterUser() {
        userService.registerUser("U", "P");
    }

    @Test
    void testRegisterExistingUser() {
        userService.registerUser("U", "P");
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser("U", "P2"));
    }

    @Test
    void testAuthenticateUser() {
        userService.registerUser("U", "P");

        assertTrue(userService.authenticateUser("U", "P"));
    }

    @Test
    void testAuthenticateInvalidUser() {
        userService.registerUser("U", "P");

        assertAll(
                () -> assertFalse(userService.authenticateUser("U2", "P")),
                () -> assertFalse(userService.authenticateUser("U", "P2"))
        );
    }

    @Test
    void testUpdatePassword() {
        userService.registerUser("U", "P");
        userService.updatePassword("U", "p");

        assertAll(
                () -> assertTrue(userService.authenticateUser("U","p")),
                () -> assertFalse(userService.authenticateUser("U", "P"))
        );
    }

    @Test
    void testUpdatePasswordNonExistingUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.updatePassword("U2", "P"));
    }

    @Test
    void testDeleteUser() {
        userService.registerUser("U", "P");
        userService.deleteUser("U");
        assertFalse(userService.authenticateUser("U", "P"));
    }

    @Test
    void testDeleteNonExistingUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser("U2"));
    }
}