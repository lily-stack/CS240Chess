package passoffTests.serverTests;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import DataAccess.UserDao;
import Models.AuthToken;
import Models.GameModel;
import Models.User;
import Responses.JoinGameResponse;
import Responses.ListGamesResponse;
import Responses.RegisterResponse;
import Services.*;
import chess.ChessGame;
import chess.game;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

import java.util.HashSet;

public class UnitTests {
    String USER1 = "user1";
    String USER2 = "user2";
    String PASSWORD1 = "password1";
    String PASSWORD2 = "password2";
    String EMAIL1 = "email1";
    String EMAIL2 = "email2";
    String AUTHTOKEN = "token123";
    UserDao userDao = new UserDao();
    AuthDao authDao = new AuthDao();
    GameDao gameDao = new GameDao();
    ClearService clearService = new ClearService();
    @Test
    public void ClearAll() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        int status = 200;
        User newUser = new User();
        newUser.username = "clearuser";
        newUser.password = "clearpassword";
        newUser.email = "clearemail";

        try {
            userDao.Insert(newUser);
            Assertions.assertEquals(200,userDao.FindUser(newUser.username, newUser.password));
            clearService.clear();
        }
        catch(DataAccessException e){
            Assertions.fail();
        }
        Assertions.assertEquals(401, userDao.FindUser(newUser.username, newUser.password));
        try {
            userDao.Insert(newUser);
            Assertions.assertEquals(200,userDao.FindUser(newUser.username, newUser.password));
            clearService.clear();
            clearService.clear();
            clearService.clear();
        }
        catch(DataAccessException e){
            Assertions.fail();
        }
    }
    @Test
    public void CreateGameServicePassTest()throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        CreateGameService gameservice = new CreateGameService();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authorization = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertEquals(200, gameservice.create(game, AUTHTOKEN));
        Assertions.assertEquals(USER1,authDao.Find(authToken.authorization));
        Assertions.assertEquals(1, (gameDao.Find(game.gameID)).gameID);
    }
    @Test
    public void CreateGameServiceFailTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        CreateGameService gameservice = new CreateGameService();
        GameModel game = new GameModel();
        game.gameID = 1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authorization = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertEquals(USER1,authDao.Find(authToken.authorization));
        Assertions.assertEquals(401, gameservice.create(game, "notToken"));
        GameModel newGame = new GameModel();
        //Assertions.assertEquals(400, gameservice.create(newGame, AUTHTOKEN));
    }
    @Test
    public void RegisterServicePassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertNotNull(response.authorization);
        Assertions.assertEquals(200, response.status);
        Assertions.assertEquals(EMAIL1,response.getEmail());
        Assertions.assertEquals(USER1,response.getUsername());
        Assertions.assertEquals(PASSWORD1,response.getPassword());
        try {
            userDao.FindUser(USER1, PASSWORD1);
        }
        catch(DataAccessException e){
            Assertions.fail("failed register test: could not find user");
        }
    }
    @Test
    public void RegisterServiceFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = new RegisterResponse();
        response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(USER1, response.getUsername());
        Assertions.assertEquals(EMAIL1, response.getEmail());
        Assertions.assertEquals(PASSWORD1, response.getPassword());
        Assertions.assertNotNull(response.getAuthToken());
        response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(403, response.getStatus());
        Assertions.assertNull(response.getAuthToken());
        Assertions.assertEquals(USER1, response.getUsername());
    }
    @Test
    public void LoginPassTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
        Assertions.assertEquals(EMAIL1,response.getEmail());
        Assertions.assertEquals(PASSWORD1,response.getPassword());
        Assertions.assertEquals(USER1, response.getUsername());
        LoginService loginService = new LoginService();
        Assertions.assertEquals(200, loginService.loginTest(USER1, PASSWORD1));
        Assertions.assertNotNull(loginService.login(USER1, PASSWORD1));
    }
    @Test
    public void LoginFailTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
        Assertions.assertEquals(EMAIL1,response.getEmail());
        Assertions.assertEquals(PASSWORD1,response.getPassword());
        Assertions.assertEquals(USER1, response.getUsername());
        LoginService loginService = new LoginService();
        Assertions.assertEquals(401, loginService.loginTest(USER2,PASSWORD2));
        Assertions.assertNull(loginService.login(USER2, PASSWORD2));
    }
    @Test
    public void LogoutPassTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.email = EMAIL1;
        user.password = PASSWORD1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authorization = AUTHTOKEN;
        authDao.Insert(authToken);
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
        Assertions.assertEquals(EMAIL1,response.getEmail());
        Assertions.assertEquals(PASSWORD1,response.getPassword());
        Assertions.assertEquals(USER1, response.getUsername());
        LogoutService logoutService = new LogoutService();
        Assertions.assertEquals(200, logoutService.logout(AUTHTOKEN));
        Assertions.assertNull(authDao.Find(AUTHTOKEN));
    }
    @Test
    public void LogoutFailTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.email = EMAIL1;
        user.password = PASSWORD1;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(USER1, PASSWORD1, EMAIL1);
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
        Assertions.assertEquals(EMAIL1,response.getEmail());
        Assertions.assertEquals(PASSWORD1,response.getPassword());
        Assertions.assertEquals(USER1, response.getUsername());
        Assertions.assertNotNull(response.authorization);
        Assertions.assertEquals(USER1,authDao.Find(response.authorization));
        LogoutService logoutService = new LogoutService();
        Assertions.assertEquals(401, logoutService.logout("fakeAuth"));
    }
    @Test
    public void ListGamesPassTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.game = new game();
        gameDao.Insert(game);
        GameModel game2 = new GameModel();
        game2.gameID = 2;
        game2.game = new game();
        gameDao.Insert(game2);
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        HashSet<GameModel> gameList = new HashSet<>();
        gameList.add(game);
        gameList.add(game2);
        //Assertions.assertEquals(gameList, gameDao.FindAll());

        ListGamesService listGamesService = new ListGamesService();
        ListGamesResponse response = listGamesService.listGames(AUTHTOKEN);
        Assertions.assertEquals(200,response.getStatus() );
        Assertions.assertEquals(gameList.size(),response.games.size());
    }
    @Test
    public void ListGamesFailTest()throws DataAccessException{
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        GameModel game2 = new GameModel();
        game2.gameID = 2;
        gameDao.Insert(game2);
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        HashSet<GameModel> gameList = new HashSet<>();
        gameList.add(game);
        gameList.add(game2);
        Assertions.assertEquals(gameList, gameDao.FindAll());
        ListGamesService listGamesService = new ListGamesService();
        Assertions.assertEquals(401, listGamesService.listGames("notToken").getStatus());
    }
    @Test
    public void JoinGamePassTest()throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        Assertions.assertEquals(USER1,authDao.Find(AUTHTOKEN));
        JoinGameService joinService = new JoinGameService();
        JoinGameResponse response = joinService.join(1,AUTHTOKEN,"WHITE");
        Assertions.assertEquals(200, response.status);
        Assertions.assertEquals(null, response.blackUser);
        Assertions.assertEquals(USER1, response.whiteUser);

    }
    @Test
    public void JoinGameFailTest()throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        gameDao.Insert(game);
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        JoinGameService joinService = new JoinGameService();
        //Assertions.assertThrows(DataAccessException.class, () -> joinService.join(4,AUTHTOKEN,"WHITE"));
        JoinGameResponse response = joinService.join(4,AUTHTOKEN,"WHITE");
        Assertions.assertEquals(400, response.status);
        response = joinService.join(1,"NotToken","WHITE");
        Assertions.assertEquals(401, response.status);
        response = joinService.join(1,"","");
        Assertions.assertEquals(401, response.status);
    }
}
