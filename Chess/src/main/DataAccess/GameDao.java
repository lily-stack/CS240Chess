package DataAccess;

import Models.GameModel;
import deserializer.DeserializeGame;
import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Data Access Object(Dao) class for storing and retrieving the server's data for the game
 */
public class GameDao {
    public static Database database = new Database();
    //public static int game_ID = -1;
    /**
     * a map for ordering the game's and being able to easily find and access them
     */
    //public static HashMap<Integer, GameModel> gameList = new HashMap<>();

    /**
     * a method for inserting a new game into the database
     *
     * @param game of type GameModel
     */
    /*public void Insert(GameModel game)throws DataAccessException{
        gameList.put(game.gameID, game);
    }*/
    public void Insert(GameModel game) throws DataAccessException {
        System.out.println("get connection from Insert in GameDao");
        Connection connection = database.getConnection();
        int gameID = game.gameID;
        String whiteUsername = game.whiteUsername;
        String blackUsername = game.blackUsername;
        String gameName = game.gameName;
        ChessGame chessGame = game.game;
        if ((whiteUsername == null || whiteUsername.matches("[a-zA-Z0-9]+")) && (blackUsername == null || blackUsername.matches("[a-zA-Z0-9]+"))) {
            if(gameID > 0) {
                var statement = "INSERT INTO gameDao (gameId, whiteUsername, blackUsername, gameName, game) VALUES(?,?,?,?,?)";
                try (var preparedStatement = connection.prepareStatement(statement)) {
                    preparedStatement.setInt(1, gameID);
                    preparedStatement.setString(2, whiteUsername);
                    preparedStatement.setString(3, blackUsername);
                    preparedStatement.setString(4, gameName);
                    preparedStatement.setString(5, new Gson().toJson(chessGame));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                var statement = "INSERT INTO gameDao (whiteUsername, blackUsername, gameName, game) VALUES(?,?,?,?)";
                try (var preparedStatement = connection.prepareStatement(statement)) {
                    preparedStatement.setString(1, whiteUsername);
                    preparedStatement.setString(2, blackUsername);
                    preparedStatement.setString(3, gameName);
                    preparedStatement.setString(4, new Gson().toJson(chessGame));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    database.returnConnection(connection);
                    throw new RuntimeException(e);
                }
                var statement2 = "SELECT gameId FROM gameDao ORDER BY gameId DESC LIMIT 1";
                try (var preparedStatement = connection.prepareStatement(statement2)) {
                    try (var rs = preparedStatement.executeQuery()) {
                        while (rs.next()) {
                            gameID = rs.getInt("gameId");
                        }

                    }
                } catch (SQLException e) {
                    database.returnConnection(connection);
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            //throw new SQLException();
            database.returnConnection(connection);
            throw new DataAccessException("can't access sql data");
        }
        game.gameID = gameID;
        database.returnConnection(connection);
    }

    /**
     * a method for retrieving a specified game from the database by gameID
     *
     * @param gameID of type int
     * @return a collection of GameModel objects
     */
    /*public GameModel Find(int gameID)throws DataAccessException{
        return gameList.get(gameID);
    }*/

    public GameModel Find(int gameID)throws DataAccessException{
        System.out.println("get connection from Find in GameDao");
        Connection connection = database.getConnection();

        DeserializeGame dg = new DeserializeGame();

        var statement = "SELECT * FROM gameDao WHERE gameID=" + Integer.toString(gameID);
        String game = null;
        String whiteUser = null;
        String blackUser = null;
        String gameName = null;
        GameModel aGame = new GameModel();
        try (var preparedStatement = connection.prepareStatement(statement)) {
            //preparedStatement.setString(1, Integer.toString(gameID));
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    game = rs.getString("game");
                    whiteUser = rs.getString("whiteUsername");
                    blackUser = rs.getString("blackUsername");
                    gameName = rs.getString("gameName");
                }
            }
        } catch (SQLException e) {
            database.returnConnection(connection);
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);

        if(game == null){
            throw new DataAccessException("Game not found");
        }
        else{
            ChessGame chessGame = dg.deserializeGame(game);
            aGame.whiteUsername = whiteUser;
            aGame.blackUsername = blackUser;
            aGame.gameID = gameID;
            aGame.gameName = gameName;
            aGame.game =chessGame;
            //return new Gson().fromJson(game, GameModel.class);
            //var chessGame = new Gson().fromJson(game, GameModel.class);
            //return new Gson().fromJson(aGame, GameModel.class);
            return aGame;
        }
    }

    /**
     * a method for retrieving all games from the database
     * @return a collection of GameModel objects
     */
    /*public Collection<GameModel> FindAll()throws DataAccessException{
        return gameList.values();
    }*/

    public Collection<GameModel> FindAll()throws DataAccessException{
        System.out.println("get connection from FindAll in GameDao");
        Connection connection = database.getConnection();

        var statement = "SELECT * FROM gameDao";
        HashSet<GameModel> gameList = new HashSet<>();
        int gameId;
        String whiteUser;
        String blackUser;
        String gameName;
        try (var preparedStatement = connection.prepareStatement(statement)) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    GameModel tempGame = new GameModel();
                    tempGame.gameID = rs.getInt("gameId");
                    tempGame.whiteUsername = rs.getString("whiteUsername");
                    tempGame.blackUsername = rs.getString("blackUsername");
                    tempGame.gameName = rs.getString("gameName");
                    gameList.add(tempGame);
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
        return gameList;
    }
    /**
     * a method for updating a chessGame in the database. It replaces the chessGame string corresponding to a given gameID with a new chessGame string
     * @param gameID of type int
     */
    public void UpdateGame(int gameID, GameModel game) throws DataAccessException{
        System.out.println("get connection from UpdateGame in GameDao");
        if(Find(gameID) == null){
            throw new DataAccessException("invalid gameID");
        }
        Connection connection = database.getConnection();

        try (var preparedStatement = connection.prepareStatement("UPDATE gameDao SET gameName=?, whiteUsername=?, blackUsername=?, game=? WHERE gameId=?")) {
            //preparedStatement.setString(1, new Gson().toJson(game));
            preparedStatement.setString(1, game.gameName);
            preparedStatement.setString(2, game.whiteUsername);
            preparedStatement.setString(3, game.blackUsername);
            preparedStatement.setString(4, new Gson().toJson(game.game));
            preparedStatement.setInt(5, gameID);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
    }
    public void claimSpot(String color, int gameId, String username)throws DataAccessException{
        GameModel game = Find(gameId);
        if(color != null){
            if(Objects.equals(color, "WHITE") || Objects.equals(color, "white")){
                game.setWhiteUsername(username);
            }
            else if(Objects.equals(color, "BLACK") || Objects.equals(color, "black")){
                game.setBlackUsername(username);
            }
        }
        UpdateGame(gameId, game);
    }
    /**
     * a method for clearing all data from the database about the game
     */
    /*public void clear(){
        gameList.clear();
    }*/
    public void clear()throws DataAccessException{
        System.out.println("get connection from clear in GameDoq");
        Connection connection = database.getConnection();
        try (var preparedStatement = connection.prepareStatement("DELETE FROM gameDao")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        database.returnConnection(connection);
    }
}
