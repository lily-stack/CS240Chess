package Services;

import DataAccess.AuthDao;
import Models.AuthToken;
import Requests.LogoutRequest;
import Responses.LogoutResponse;
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
    public int logout(String authToken) {
        try {
            authDao.Find(authToken);
        }
        catch(DataAccessException e){
            return 401;
        }
        authDao.deleteToken(authToken);
        return 200;
    }
}
