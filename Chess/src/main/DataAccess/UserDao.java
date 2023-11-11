package DataAccess;

import Models.AuthToken;
import Models.GameModel;
import Models.User;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the user
 */
public class UserDao {
    public static Database database = new Database();
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
        if(username == null || password == null){
            throw new DataAccessException("username or password null when shouldn't be");
        }
        Insert(newUser);
    }
    /**
     * a method for clearing all data from the database about the user
     */
    /*public void clear(){
        userList.clear();
    }*/

    public void clear() throws DataAccessException {
        System.out.println("get connection from clear in userDao");
        Connection connection = database.getConnection();
        try (var preparedStatement = connection.prepareStatement("DELETE FROM userDao")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            database.returnConnection(connection);
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
    }
    /**
     * a method for inserting a new user into the database
     *
     * @param user of type User
     */
    /*public void Insert(User user){
        userList.put(user.username, user);
    }*/
    public void Insert(User user) throws DataAccessException {
        System.out.println("get connection from insert in userDao");
        Connection connection = database.getConnection();
        String username = user.username;
        String password = user.password;
        String email = user.email;

        try{
            if (username.matches("[a-zA-Z0-9]+") && password.matches("[a-zA-Z0-9]+") && email.matches("[a-zA-Z0-9.@]+")) {
                var statement = "INSERT INTO userDao (username, password, email) VALUES(?,?,?)";
                try (var preparedStatement = connection.prepareStatement(statement)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);
                    preparedStatement.executeUpdate();
                }
            }
            else {
                database.returnConnection(connection);
                throw new SQLException();
            }
        }
        catch(SQLException e){
            database.returnConnection(connection);
            throw new DataAccessException("can't insert new user");
        }
        database.returnConnection(connection);
    }
    /**
     * a method for finding a specific object based on a username
     */
    /*public int FindUser(String username, String password)throws DataAccessException{
        if(userList.containsKey(username)){
            if(Objects.equals(userList.get(username).password, password)){
                return 200;
            }
        }
        return 401;
    }*/

    public int FindUser(String username, String password)throws DataAccessException{
        System.out.println("get connection from FindUser in userDao");
        Connection connection = database.getConnection();

        var statement = "SELECT username, password FROM userDao WHERE username=\"" + username + "\"";
        String name = null;
        String pw = null;
        try (var preparedStatement = connection.prepareStatement(statement)) {
            //preparedStatement.setString(1, username);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("username");
                    pw = rs.getString("password");
                }
            }

        } catch (SQLException e) {
            database.returnConnection(connection);
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
        if(name == null){
            //throw new DataAccessException("user not found");
            return 401;
        }
        else{
            if(!Objects.equals(pw, password)){
                return 401;
            }
            return 200;//new Gson().fromJson(name, int.class);
        }
    }


}
