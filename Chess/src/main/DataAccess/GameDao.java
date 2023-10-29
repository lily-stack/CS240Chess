package DataAccess;

import Models.AuthToken;
import Models.GameModel;
import Models.User;
import dataAccess.DataAccessException;

import java.util.*;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the game
 */
public class GameDao {
    int gameID;
    /**
     * a map for ordering the game's and being able to easily find and access them
     */
    public static HashMap<Integer, GameModel> gameList = new HashMap<>();
    /**
     * creates a new game object
     * @param game of type GameModel
     * @throws DataAccessException throws an exception if user can't be created
     */
    void CreateGame(GameModel game) throws DataAccessException{}
    /**
     * a method for inserting a new game into the database
     *
     * @param game of type GameModel
     */
    public void Insert(GameModel game)throws DataAccessException{
        gameList.put(game.gameID, game);
    }

    /**
     * a method for retrieving a specified game from the database by gameID
     *
     * @param gameID of type int
     * @return a collection of GameModel objects
     */
    public GameModel Find(int gameID)throws DataAccessException{
        return gameList.get(gameID);
    }

    /**
     * a method for retrieving all games from the database
     * @return a collection of GameModel objects
     */
    public Collection<GameModel> FindAll()throws DataAccessException{
        return gameList.values();

    }

    /**
     * a method for updating a chessGame in the database. It replaces the chessGame string corresponding to a given gameID with a new chessGame string
     * @param gameID of type int
     * @param game of type GameModel
     * @throws DataAccessException throws an exception if game can't be updated
     */
    public void UpdateGame(int gameID, GameModel game)throws DataAccessException{}

    /**
     * a method for claiming a spot in the game and the username will be saved as either the black or white player
     * @param username of type User
     *@param color of type String
     */
    public void ClaimSpot(User username, String color){}

    /**
     * a method for clearing all data from the database about the game
     */
    public void clear(){
        gameList.clear();
    }
}
