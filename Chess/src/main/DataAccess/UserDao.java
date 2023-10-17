package DataAccess;

import Models.User;
import dataAccess.DataAccessException;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the user
 */
public class UserDao {
    /**
     * creates a new user object
     * @param u of type User
     * @throws DataAccessException throws an exception if user can't be created
     */
    void CreateUser(User u) throws DataAccessException{}

    /**
     * a method for claiming a spot in the game and the username will be saved as either the black or white player
     * @param username of type User
     */
    void ClaimSpot(User username){}
}
