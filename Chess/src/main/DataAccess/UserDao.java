package DataAccess;

import Models.GameModel;
import Models.User;
import dataAccess.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the user
 */
public class UserDao {
    /**
     * a map for ordering all users and being able to easily find and access them
     */
    public static HashMap<String, User> userList = new HashMap<>();
    /**
     * creates a new user object
     * @param username of type String
     * @param password of type String
     * @param email of type String
     * @throws DataAccessException throws an exception if user can't be created
     */
    public void CreateUser(String username, String password, String email) throws DataAccessException{
        User newUser = new User();
        newUser.email = email;
        newUser.password = password;
        newUser.username = username;
        Insert(newUser);
    }
    /**
     * deletes a user object
     * @param u of type User
     * @throws DataAccessException throws an exception if user can't be deleted
     */
    public void DeleteUser(User u) throws DataAccessException{}
    /**
     * a method for clearing all data from the database about the user
     */
    public void clear(){
        userList.clear();
    }
    /**
     * a method for inserting a new user into the database
     *
     * @param user of type User
     */
    public void Insert(User user)throws DataAccessException{
        userList.put(user.username, user);
    }
    /**
     * a method for finding a specific object based on a username
     */

    public int FindUser(String username, String password)throws DataAccessException{
        if(userList.containsKey(username)){
            if(Objects.equals(userList.get(username).password, password)){
                return 200;
            }
        }
        return 401;
    }


}
