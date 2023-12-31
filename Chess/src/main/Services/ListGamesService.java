package Services;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import Responses.ListGamesResponse;
import dataAccess.DataAccessException;

/**
 * implements the logic for an HTTP GET method for listing all games
 *
 */
public class ListGamesService {
    private AuthDao authDao = new AuthDao();
    private GameDao gameDao = new GameDao();
    /**
     * Receives a ListGamesRequest object and returns a corresponding ListGamesResponse object
     *
     * @return ListGamesResponse
     */
    public ListGamesResponse listGames(String authToken) throws DataAccessException {
        ListGamesResponse listGamesResponse = new ListGamesResponse();
        if(authDao.Find(authToken) == null){
            listGamesResponse.setStatus(401);
            return listGamesResponse;
        }
        try{
            listGamesResponse.setGames(gameDao.FindAll());
            listGamesResponse.setStatus(200);
            return listGamesResponse;
        }
        catch(DataAccessException e){
            listGamesResponse.setStatus(401);
            return listGamesResponse;
        }
    }
    public int checkGameList(String authToken) throws DataAccessException {
        try{
            gameDao.FindAll();
        }
        catch(DataAccessException e){
            return 500;
        }
        if(authDao.Find(authToken) == null){
            return 401;
        }
        return 200;
    }

}
