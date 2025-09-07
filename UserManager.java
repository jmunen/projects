
import java.util.*;

/**
 * Manages all users in the appointment system.
 */
public class UserManager {
    private Map<String, User> users;
    
    public UserManager() {
        this.users = new HashMap<>();
    }
    
    /**
     * Adds a new user to the system.
     * @param username The username for the new user
     * @return true if user added successfully, false if username already exists
     */
    public boolean addUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        if (users.containsKey(username)) {
            return false;
        }
        
        users.put(username, new User(username));
        return true;
    }
    
    /**
     * Deletes a user from the system.
     * @param username The username to delete
     * @return true if deleted successfully, false if user not found
     */
    public boolean deleteUser(String username) {
        return users.remove(username) != null;
    }
    
    /**
     * Gets a user by username.
     * @param username The username to look up
     * @return The User object if found, null otherwise
     */
    public User getUser(String username) {
        return users.get(username);
    }
    
    /**
     * Checks if a user exists in the system.
     * @param username The username to check
     * @return true if user exists, false otherwise
     */
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    /**
     * Gets a list of all usernames in the system.
     * @return List of usernames sorted alphabetically
     */
    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>(users.keySet());
        Collections.sort(usernames);
        return usernames;
    }
    
    /**
     * Gets the total number of users in the system.
     * @return Number of users
     */
    public int getUserCount() {
        return users.size();
    }
    
    /**
     * Checks if the system has any users.
     * @return true if system has users, false if empty
     */
    public boolean hasUsers() {
        return !users.isEmpty();
    }
}