package Models;

/**
 * a class to store the authenticator token and username
 */
public class AuthToken {
    /**
     * string of token for authorization
     */
    public String authToken;
    /**
     * string to represent the username
     */
    public String username;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
