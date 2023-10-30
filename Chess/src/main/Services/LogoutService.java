package Services;

import DataAccess.AuthDao;

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
        if(authDao.Find(authToken) == null){
            return 401;
        }

        authDao.deleteToken(authToken);
        return 200;
    }
}
