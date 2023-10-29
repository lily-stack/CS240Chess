package Services;

import DataAccess.UserDao;
import Requests.RegisterRequest;
import Responses.RegisterResponse;
import dataAccess.DataAccessException;

/**
 *implements the logic for an HTTP POST method for registering a user for a game
 */
public class RegisterService {
    private UserDao userDao = new UserDao();
    LoginService loginService = new LoginService();
    /**
     * Receives a registerRequest object and returns a corresponding registerResponse object
     *
     * @param email of type String
     * @param  password of type String
     * @param username of type String
     * @return RegisterResponse
     */
    public RegisterResponse register(String username, String password, String email) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(200);
        registerResponse.setUsername(username);
        try{
            if (userDao.FindUser(username, password) != 200){
                throw new Exception("Exception message");
            }
        }
        catch(DataAccessException e){
             registerResponse.setStatus(400);
        }
        catch (Exception e) {
            registerResponse.setStatus(403);
        }
        try{
            userDao.CreateUser(username, password, email);
        }
        catch(DataAccessException e){
            registerResponse.setStatus(400);
        }
        registerResponse.setAuthToken(loginService.login(username, password).authToken);
        return registerResponse;
    }
}
