package at.htlsaalfelden;

import java.util.HashMap;
import java.util.Map;

/*
UserService class with additional methods for updating user credentials 
and deleting user accounts

Generate the following test cases
- testRegisterUser
- testRegisterExistingUser
- testAuthenticateUser
- testAuthenticateInvalidUser
- testUpdatePassword
- testUpdatePasswordNonExistingUser
- testDeleteUser
- testDeleteNonExistingUser
*/
public class UserService {
    private Map<String, String> userCredentials;

    public UserService() {
        userCredentials = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        userCredentials.put(username, password);
    }

    public boolean authenticateUser(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public void updatePassword(String username, String newPassword) {
        if (!userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("Username does not exist");
        }
        userCredentials.put(username, newPassword);
    }

    public void deleteUser(String username) {
        if (!userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("Username does not exist");
        }
        userCredentials.remove(username);
    }
}
