package Requests;

/**
 * Represents a request for joining a game and contains the variables username and password
 */
public class JoinGameRequest {
    /**
     * string representing the color of a player
     */
    private String playerColor;
    /**
     * int representing the ID of a game
     */
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
