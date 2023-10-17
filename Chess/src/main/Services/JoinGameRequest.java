package Services;

/**
 * Represents a request for joining a game and contains the variables username and password
 */
public class JoinGameRequest {
    private String playerColor;
    private int gameID;

    /**
     * constructor for creating an instance of a request to join a game
     */
    public JoinGameRequest() {}

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }
}
