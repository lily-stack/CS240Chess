package DataAccess;

import Models.AuthToken;
import Models.GameModel;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.DriverManager;
import java.sql.*;
import java.util.*;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for authentication
 */
public class AuthDao {
    public static Database database = new Database();
    /**
     * a map for ordering the authentication tokens and being able to easily find and access them
     */
    //public static HashMap<String, AuthToken> authenticationList = new HashMap<>();

    /**
     * creates a new Authorization object
     * @param user of type String
     * @throws DataAccessException throws an exception if authentication can't be created
     */
    /*public AuthToken CreateAuth(String user) throws DataAccessException{
        String authToken = UUID.randomUUID().toString();
        AuthToken newAuthToken = new AuthToken();
        newAuthToken.setAuthToken(authToken);
        newAuthToken.setUsername(user);
        authenticationList.put(authToken, newAuthToken);
        return newAuthToken;
    }*/

    public AuthToken CreateAuth(String user) throws DataAccessException{
        System.out.println("get connection from CreateAuth in AuthDao");
        Connection connection = database.getConnection();
        String authToken = UUID.randomUUID().toString();
        try{
            if (user.matches("[a-zA-Z0-9]+")) {
                var statement = "INSERT INTO authDao (authToken, username) VALUES(?,?)";
                try (var preparedStatement = connection.prepareStatement(statement)) {
                    preparedStatement.setString(1, authToken);
                    preparedStatement.setString(2, user);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                throw new DataAccessException("invalid username");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
        AuthToken newAuthToken = new AuthToken();
        newAuthToken.setAuthToken(authToken);
        newAuthToken.setUsername(user);
        return newAuthToken;
    }
    /**
     * a method for clearing all data from the database
     */
    /*public void Clear(){
        authenticationList.clear();
    }*/
    public void Clear() throws DataAccessException {
        System.out.println("get connection from clear in authDao");
        Connection connection = database.getConnection();
        //try (var preparedStatement = connection.prepareStatement("TRUNCATE authDao")) {
        try (var preparedStatement = connection.prepareStatement("DELETE FROM authDao")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            database.returnConnection(connection);
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
    }

    /**
     * a method for deleting a token
     * @param token of type string
     */
    /*public void deleteToken(String token){
        authenticationList.remove(token);
    }*/
    public void deleteToken(String token) throws DataAccessException {
        System.out.println("get connection from deleteToken in AuthDao");

        if(Find(token) != null){
            Connection connection = database.getConnection();
            var statement = "DELETE FROM authDao WHERE authToken=?";
            try (var preparedStatement = connection.prepareStatement(statement)){
                preparedStatement.setString(1, token);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                database.returnConnection(connection);
                throw new DataAccessException("Error:");
            }
            database.returnConnection(connection);
        }
        else{
            throw new DataAccessException("invalid authToken");
        }
    }
    /**
     * a method for inserting an authToken value
     * @param authToken of type AuthToken
     * @throws DataAccessException throws an exception if authentication token can't be inserted
     */
    /*public void Insert(AuthToken authToken)throws DataAccessException{
        if(!authenticationList.containsKey(authToken.getAuthToken())){

            authenticationList.put(authToken.authToken, authToken);
        }
        else{
            throw new DataAccessException("Error: already taken");
        }
    }*/
    public void Insert(AuthToken authToken) throws DataAccessException {
        System.out.println("get connection from insert in AuthDao");
        Connection connection = database.getConnection();
        String username = authToken.username;
        String token = authToken.authToken;
        if (username.matches("[a-zA-Z0-9]+") && token.matches("[a-zA-Z0-9]+")) {
            var statement = "INSERT INTO authDao (authToken, username) VALUES(?,?)";
            try (var preparedStatement = connection.prepareStatement(statement)) {
                preparedStatement.setString(1, token);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                database.returnConnection(connection);
                throw new RuntimeException(e);
            }
        }
        else {
            database.returnConnection(connection);
            throw new DataAccessException("Error: already taken");
        }
        database.returnConnection(connection);
    }

    /**
     * a method for finding a specific object based on an authToken
     *@param token of type String
     */
    /*public String Find(String token){
        if(authenticationList.get(token) != null) {
            return authenticationList.get(token).username;
        }
        return null;
    }*/



    public String Find(String token) throws DataAccessException {
        System.out.println("get connection from find in authdao");
        Connection connection = database.getConnection();

        var statement = "SELECT username FROM authDao WHERE authToken=\"" + token + "\"";
        String username = null;
        try (var preparedStatement = connection.prepareStatement(statement)) {
            //preparedStatement.setString(1, token);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            database.returnConnection(connection);
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
        if(username == null){
            //throw new DataAccessException("user not found");
            return null;
        }
        else{
            return new Gson().fromJson(username, String.class);
        }
    }

}
