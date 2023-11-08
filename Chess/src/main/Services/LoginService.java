package Services;

import DataAccess.AuthDao;
import DataAccess.UserDao;
import Models.AuthToken;
import dataAccess.DataAccessException;

/**
 * implements the logic for an HTTP Post method for logging into a game
 */
public class LoginService {
    private UserDao userDao = new UserDao();
    private AuthDao authDao = new AuthDao();
    /**
     * Receives a LoginRequest object and returns a corresponding LoginResponse object
     *
     * @param username of type String
     * @param password of type String
     * @return LoginResponse
     */
    public int loginTest(String username, String password){
        try {
            if(userDao.FindUser(username, password) == 401){
                return 401;
            };
        }
        catch(DataAccessException e){
            return 401;
        }
        return 200;
    }
    public AuthToken login(String username, String password) {
        try {
            if (userDao.FindUser(username, password) != 200){
                return null;
            }
        }
        catch(DataAccessException e){
            return null;
        }
        try {
            return authDao.CreateAuth(username);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
