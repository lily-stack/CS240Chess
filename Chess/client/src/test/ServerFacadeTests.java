
import Requests.RegisterRequest;
import Responses.RegisterResponse;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Server.ServerFacade;
import Models.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ServerFacadeTests {
    private final String username = "testUserName";
    private final String password = "testPassword";
    private final String email = "email@test.org";
    private String authToken = "a@tl7";
    private String gameName = "Game1";

    ServerFacade server = new ServerFacade("http://localhost:8080/");
    @BeforeEach
    public void refresh() throws ResponseException {
        server.clear();
        var path = "/user";
        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setUsername(username);
        request.setPassword(password);
        RegisterResponse response = server.makeRequest("POST", path, request, RegisterResponse.class);
        authToken = response.getAuthToken();
    }
    @Test
    public void createGamePass() throws ResponseException {
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName,authToken));
    }
    @Test
    public void createGameFail() throws ResponseException {
        Assertions.assertThrows(ResponseException.class,() ->server.createGame(gameName = null, authToken = null));
    }
    @Test
    public void RegisterUserPass() throws ResponseException {
        User user = new User("username2", "password2", "email2");
        Assertions.assertDoesNotThrow(() -> server.registerUser(user));
        User user2 = new User("username3", "password3", "email3");
        var authentication = server.registerUser(user2);
        Assertions.assertNotNull(authentication);
    }
    @Test
    public void RegisterUserFail() throws ResponseException {
        User user = new User("username2", null, "email2");
        Assertions.assertThrows(ResponseException.class,() ->server.registerUser(user));
        User user2 = new User(null, password, email);
        Assertions.assertThrows(ResponseException.class,() ->server.registerUser(user2));
    }
    @Test
    public void LoginUserPass() throws ResponseException {
        User user = new User(username + "2", password + "2", email + "2");
        server.registerUser(user);
        Assertions.assertDoesNotThrow(() -> server.loginUser(username, password));
    }
    @Test
    public void LoginUserFail() throws ResponseException {
        User user = new User(username + "2", password + "2", email + "2");
        Assertions.assertDoesNotThrow(() -> server.registerUser(user));
        Assertions.assertThrows(ResponseException.class,() -> server.loginUser("NotUser", password));
        Assertions.assertThrows(ResponseException.class,() -> server.loginUser(username, "NotPassword"));
    }
    @Test
    public void LogoutUserPass() throws ResponseException {
        Assertions.assertDoesNotThrow(() -> server.logoutUser(authToken));
    }
    @Test
    public void LogoutUserFail() throws ResponseException {
        Assertions.assertDoesNotThrow(() -> server.loginUser(username, password));
        Assertions.assertThrows(ResponseException.class,() -> server.logoutUser("NotUser"));
    }
    @Test
    public void ListGamesPass() throws ResponseException {
        String gameName2 = "game2";
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName,authToken));
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName2,authToken));
        Assertions.assertDoesNotThrow(() -> server.listGames(authToken));
        List<GameModel> fromGameList = (List<GameModel>) server.listGames(authToken);
        HashSet<String> gameList = new HashSet<>();
        gameList.add(gameName);
        gameList.add(gameName2);
        Assertions.assertEquals(gameList.size(),fromGameList.size());
    }
    @Test
    public void ListGamesFail() throws ResponseException {
        Assertions.assertThrows(ResponseException.class,() -> server.listGames("notAToken"));
    }
    @Test
    public void JoinGamePass() throws ResponseException {
        User user = new User("newUser","newPassword", "newEmail@byu.edu");
        String newToken = server.registerUser(user);
        server.createGame(gameName, newToken);
        Collection<GameModel> gameList = server.listGames(newToken);
        int gameID = 0;
        for (GameModel game : gameList){
            gameID = game.gameID;
        }
        int finalGameID = gameID;
        Assertions.assertDoesNotThrow(() -> server.joinGame(finalGameID,"WHITE", newToken));
        Assertions.assertDoesNotThrow(() -> server.joinGame(finalGameID,"BLACK", newToken));
        Assertions.assertThrows(ResponseException.class,() -> server.joinGame(finalGameID,"BLACK", newToken));
    }
    @Test
    public void JoinGameFail() throws ResponseException {
        User user = new User("newUser","newPassword", "newEmail@byu.edu");
        String newToken = server.registerUser(user);
        server.createGame(gameName, newToken);
        Collection<GameModel> gameList = server.listGames(newToken);
        int gameID = 0;
        for (GameModel game : gameList){
            gameID = game.gameID;
        }
        int finalGameID = gameID;
        Assertions.assertThrows(ResponseException.class,() -> server.joinGame(finalGameID,"YELLOW", newToken));
        Assertions.assertThrows(ResponseException.class,() -> server.joinGame(-736, "WHITE", newToken));
        Assertions.assertThrows(ResponseException.class,() -> server.joinGame(finalGameID, "WHITE", "notAToken"));
    }
    @Test
    public void ObserveGamePass() throws ResponseException {
        User user = new User("newUser","newPassword", "newEmail@byu.edu");
        String newToken = server.registerUser(user);
        server.createGame(gameName, newToken);
        Collection<GameModel> gameList = server.listGames(newToken);
        int gameID = 0;
        for (GameModel game : gameList){
            gameID = game.gameID;
        }
        int finalGameID = gameID;

        Assertions.assertDoesNotThrow(() -> server.observeGame(finalGameID, authToken));
        Collection<GameModel> gameList2 = server.listGames(newToken);
        for (GameModel game : gameList2){
            Assertions.assertNotEquals(user.username, game.blackUsername);
            Assertions.assertNotEquals(user.username, game.whiteUsername);
        }
    }
    @Test
    public void ObserveGameFail() throws ResponseException {
        User user = new User("newUser","newPassword", "newEmail@byu.edu");
        String newToken = server.registerUser(user);
        server.createGame(gameName, newToken);
        Collection<GameModel> gameList = server.listGames(newToken);
        int gameID = 0;
        for (GameModel game : gameList){
            gameID = game.gameID;
        }
        int finalGameID = gameID;
        Assertions.assertThrows(ResponseException.class,() -> server.observeGame(-55, authToken));
        Assertions.assertThrows(ResponseException.class,() -> server.observeGame(finalGameID, "notAToken"));
    }

}
