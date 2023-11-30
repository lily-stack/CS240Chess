package Models;

/**
 * a class to store a user's username, password, and email
 */
public class User {
    /**
     * string representing the username
     */
    public String username;
    /**
     * string representing the password
     */
    public String password;
    /**
     * string representing a user's email
     */
    public String email;

    public User(String name, String ps, String e) {
        username = name;
        password = ps;
        email = e;
    }
    public User() {
    }
}
