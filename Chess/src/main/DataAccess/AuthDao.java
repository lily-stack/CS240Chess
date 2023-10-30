package DataAccess;

import Models.AuthToken;
import dataAccess.DataAccessException;

import java.util.*;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for authentication
 */
public class AuthDao {
    /**
     * a map for ordering the authentication tokens and being able to easily find and access them
     */
    public static HashMap<String, AuthToken> authenticationList = new HashMap<>();

    /**
     * creates a new Authorization object
     * @param user of type String
     * @throws DataAccessException throws an exception if authentication can't be created
     */
    public AuthToken CreateAuth(String user) throws DataAccessException{
        String authToken = UUID.randomUUID().toString();
        AuthToken newAuthToken = new AuthToken();
        newAuthToken.setAuthToken(authToken);
        newAuthToken.setUsername(user);
        authenticationList.put(authToken, newAuthToken);
        return newAuthToken;
    }
    /**
     * a method for clearing all data from the database
     */
    public void Clear(){
        authenticationList.clear();
    }

    /**
     * a method for deleting a token
     * @param token of type string
     */
    public void deleteToken(String token){
        authenticationList.remove(token);
    }
    /**
     * a method for inserting an authToken value
     * @param authToken of type AuthToken
     * @throws DataAccessException throws an exception if authentication token can't be inserted
     */
    public void Insert(AuthToken authToken)throws DataAccessException{
        if(!authenticationList.containsKey(authToken.getAuthToken())){

            authenticationList.put(authToken.authToken, authToken);
        }
        else{
            throw new DataAccessException("Error: already taken");
        }
    }

    /**
     * a method for finding a specific object based on an authToken
     *@param token of type String
     */
    public String Find(String token){
        if(authenticationList.get(token) != null) {
            return authenticationList.get(token).username;
        }
        return null;
    }
}
