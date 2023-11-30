import Requests.CreateGameRequest;
import Requests.JoinGameRequest;
import Requests.RegisterRequest;
import Responses.CreateGameResponse;
import Responses.JoinGameResponse;
import Responses.RegisterResponse;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Server.ServerFacade;
import Models.*;

import java.util.HashSet;
import java.util.List;

public class ServerFacadeTests {
    private String username = "testUserName";
    private String password = "testPassword";
    private String email = "email@test.org";
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
        var path = "/game";
        //authToken = "a@tl7";
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setAuthToken(authToken);
        CreateGameResponse response = server.makeRequest("POST", path, request, CreateGameResponse.class);
        Assertions.assertEquals(response.getGameName(), gameName);
        Assertions.assertEquals(response.getAuthToken(), authToken);
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
        //Assertions.assertThrows(ResponseException.class, () -> server.registerUser(user));
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
        User user = new User(username, password, email);
        server.registerUser(user);
        Assertions.assertDoesNotThrow(() -> server.loginUser(username, password));
    }
    @Test
    public void LoginUserFail() throws ResponseException {
        User user = new User(username, password, email);
        Assertions.assertDoesNotThrow(() -> server.registerUser(user));
        Assertions.assertThrows(ResponseException.class,() -> server.loginUser("NotUser", password));
        Assertions.assertThrows(ResponseException.class,() -> server.loginUser(username, "NotPassword"));
    }
    @Test
    public void LogoutUserPass() throws ResponseException {
        User user = new User(username, password, email);
        server.registerUser(user);
        Assertions.assertDoesNotThrow(() -> server.loginUser(username, password));
        Assertions.assertDoesNotThrow(() -> server.logoutUser(username));
    }
    @Test
    public void LogoutUserFail() throws ResponseException {
        User user = new User(username, password, email);
        server.registerUser(user);
        Assertions.assertDoesNotThrow(() -> server.loginUser(username, password));
        Assertions.assertThrows(ResponseException.class,() -> server.logoutUser("NotUser"));
    }
    @Test
    public void ListGamesPass() throws ResponseException {
        authToken = "a@tl7";
        String gameName2 = "game2";
        String authToken2 = "09fjie98*";
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName,authToken));
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName2,authToken2));
        Assertions.assertDoesNotThrow(() -> server.listGames());
        List<GameModel> fromGameList = List.of(server.listGames());
        HashSet<String> gameList = new HashSet<>();
        gameList.add(gameName);
        gameList.add(gameName2);
        Assertions.assertEquals(gameList.size(),fromGameList.size());
    }
    public void ListGamesFail() throws ResponseException {
        //idk

    }
    @Test
    public void JoinGamePass() throws ResponseException {
        var path = "/game";
        authToken = "a@tl7";
        server.createGame(gameName, authToken);
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(1);
        request.setPlayerColor("WHITE");
        JoinGameResponse response = server.makeRequest("POST", path, request, JoinGameResponse.class);
        Assertions.assertEquals(response.whiteUser, username);
        Assertions.assertEquals(response.getAuthToken(), authToken);
    }
    @Test
    public void JoinGameFail() throws ResponseException {
        var path = "/game";
        authToken = "a@tl7";
        server.createGame(gameName, authToken);
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(-88);
        request.setPlayerColor("WHITE");
        JoinGameResponse response = server.makeRequest("POST", path, request, JoinGameResponse.class);
        Assertions.assertEquals(401, response.status);
        Assertions.assertEquals(response.getAuthToken(), authToken);
        Assertions.assertDoesNotThrow(() -> server.joinGame(1,authToken));
        JoinGameRequest request2 = new JoinGameRequest();
        request.setGameID(1);
        request.setPlayerColor("WHITE");
        JoinGameResponse response2 = server.makeRequest("POST", path, request, JoinGameResponse.class);
        Assertions.assertEquals(401, response.status);
    }
    @Test
    public void ObserveGamePass() throws ResponseException {
        authToken = "a@tl7";
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName, authToken));
        Assertions.assertDoesNotThrow(() ->server.observeGame(1));
    }
    @Test
    public void ObserveGameFail() throws ResponseException {
        authToken = "a@tl7";
        Assertions.assertDoesNotThrow(() -> server.createGame(gameName, authToken));
        Assertions.assertThrows(ResponseException.class,() -> server.observeGame(-55));
    }

}
