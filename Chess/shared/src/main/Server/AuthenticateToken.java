package Server;

public class AuthenticateToken {
    public String authorization;
    public String username;
    public String getToken() {
        return authorization;
    }

    public void setToken(String token) {
        this.authorization = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
