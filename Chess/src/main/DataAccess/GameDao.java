package DataAccess;

import Models.GameModel;

import java.util.Collection;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the game
 */
public class GameDao {
    /**
     * a method for inserting a new game into the database
     *
     * @param game of type GameModel
     */
    public void Insert(GameModel game){}

    /**
     * a method for retrieving a specified game from the database by gameID
     *
     * @param gameID of type int
     * @return a collection of GameModel objects
     */
    public GameModel Find(int gameID){
        return null;
    }

    /**
     * a method for retrieving all games from the database
     * @return a collection of GameModel objects
     */
    public Collection<GameModel> FindAll(){
        return null;

    }

    /**
     * a method for updating a chessGame in the database. It replaces the chessGame string corresponding to a given gameID with a new chessGame string
     * @param gameID of type int
     */
    public void UpdateGame(int gameID){}
}
