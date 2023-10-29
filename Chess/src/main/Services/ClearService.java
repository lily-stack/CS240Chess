package Services;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import DataAccess.UserDao;
import Requests.ClearRequest;
import Responses.ClearResponse;
import dataAccess.DataAccessException;

import java.io.IOException;

/**
 * implements the logic for an HTTP DELETE method for deleting a game
 */
public class ClearService {
    AuthDao authDao = new AuthDao();
    UserDao userDao = new UserDao();
    GameDao gameDao = new GameDao();
    /**
     *  Receives a ClearRequest object and returns a corresponding ClearResponse object
     *
     * @return ClearResponse
     */
    public int clear()throws DataAccessException {
        authDao.Clear();
        userDao.clear();
        gameDao.clear();
        return 200;
    }
}
