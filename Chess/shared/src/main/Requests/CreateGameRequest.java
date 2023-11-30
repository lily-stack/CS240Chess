package Requests;

public class CreateGameRequest {
    public String gameName;
    public String authorization;
    public CreateGameRequest(){}

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }

    public String getAuthToken() {
        return authorization;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
