package Models;

/**
 * a class to store the authenticator token and username
 */
public class AuthToken {
    /**
     * string of token for authorization
     */
    public String authorization;
    /**
     * string to represent the username
     */
    public String username;

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }

    public String getAuthToken() {
        return authorization;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
