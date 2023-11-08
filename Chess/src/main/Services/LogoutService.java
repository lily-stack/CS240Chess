package Services;

import DataAccess.AuthDao;
import dataAccess.DataAccessException;

/**
 * implements the logic for an HTTP DELETE method for logging out of a game
 */
public class LogoutService {
    AuthDao authDao = new AuthDao();
    /**
     * Receives a LogoutRequest object and returns a corresponding LogoutResponse object
     * @param authToken of type AuthToken
     * @return LogoutResponse
     */
    public int logout(String authToken) throws DataAccessException {
        if(authDao.Find(authToken) == null){
            return 401;
        }
        try {
            authDao.deleteToken(authToken);
        }catch (DataAccessException e){
            throw new DataAccessException("Error:");
        }
        return 200;
    }
}
