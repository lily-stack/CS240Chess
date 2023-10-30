package DataAccess;

import Models.GameModel;
import dataAccess.DataAccessException;
import java.util.*;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the game
 */
public class GameDao {
    public static int game_ID = -1;
    /**
     * a map for ordering the game's and being able to easily find and access them
     */
    public static HashMap<Integer, GameModel> gameList = new HashMap<>();

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
     * a method for clearing all data from the database about the game
     */
    public void clear(){
        gameList.clear();
    }
}
