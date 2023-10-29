package Models;
import chess.ChessGame;

/**
 * a class to store the gameId, black and white username and the game name for a game
 */
public class GameModel {
    /**
     * int to identify and represent each game
     */
    public int gameID = -1;
    /**
     * string for the username of the white team player
     */
    public String whiteUsername;
    /**
     * string for the username of the black team user
     */
    public String blackUsername;
    /**
     * string representing the name of a game
     */
    public String gameName;
    /**
     * object of type chess.ChessGame to represent the model of a specific game
     */
    public chess.ChessGame game;

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
    public void setGameID(int id){
        gameID = id;
    }
    public int getGameID(){
        return gameID;
    }
    public void setWhiteUsername(String user){
        whiteUsername = user;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }
}
