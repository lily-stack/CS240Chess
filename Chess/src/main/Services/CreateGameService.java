package Services;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import Models.GameModel;
import dataAccess.DataAccessException;

/**
 * implements the logic for an HTTP POST method for joining a game
 *
 */
public class CreateGameService {
    private AuthDao authDao = new AuthDao();
    private GameDao gameDao = new GameDao();
    /**
     * Receives a CreateGameRequest object and returns a corresponding CreateGameResponse object
     *
     * @param game of type GameModel
     * @param authToken of type String
     * @throws DataAccessException throws an exception if game can't be created
     * @return CreateGameResponse
     */
    public int create(GameModel game, String authToken) throws DataAccessException {
        //GameDao.game_ID += 66;
        //game.setGameID(GameDao.game_ID);
        /*if(game.gameID == -1){
            return 400;
        }*/
        if(authDao.Find(authToken) == null){
            return 401;
        }
        try{
            gameDao.Insert(game);
        }
        catch(DataAccessException e){
            return 500;
        }
        return 200;
    }
}
