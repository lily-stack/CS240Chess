package passoffTests.serverTests;

import DataAccess.AuthDao;
import DataAccess.GameDao;
import DataAccess.UserDao;
import Models.AuthToken;
import Models.GameModel;
import Models.User;
import Services.ClearService;
import chess.ChessGame;
import chess.game;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class UnitTestsDao {
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
    @Test
    public void AuthDaoCreateAuthPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        Assertions.assertDoesNotThrow(() -> authDao.CreateAuth(USER1));
        //Assertions.assertTrue(authDao.CreateAuth(USER2).authToken.matches("[a-zA-Z0-9]+"));
        Assertions.assertEquals(AuthToken.class, authDao.CreateAuth(USER2).getClass());
    }
    @Test
    public void AuthDaoCreateAuthFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        Assertions.assertThrows(DataAccessException.class,() -> authDao.CreateAuth(USER1 + "%^&#"));

    }
    @Test
    public void AuthDaoClearTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
        Assertions.assertDoesNotThrow(() -> authDao.Clear());
        Assertions.assertNull(authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoDeleteTokenPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
        Assertions.assertDoesNotThrow(() -> authDao.deleteToken(AUTHTOKEN));
        Assertions.assertNull(authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoDeleteTokenFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        authDao.Insert(token);
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
        Assertions.assertThrows(DataAccessException.class,() -> authDao.deleteToken("notToken"));
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoInsertPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        Assertions.assertDoesNotThrow(() -> authDao.Insert(token));
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoInsertFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1 + "%^&*&";
        Assertions.assertThrows(DataAccessException.class,() -> authDao.Insert(token));
        Assertions.assertNull(authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoFindPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        Assertions.assertDoesNotThrow(() -> authDao.Insert(token));
        Assertions.assertEquals(USER1, authDao.Find(AUTHTOKEN));
    }
    @Test
    public void AuthDaoFindFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        AuthToken token = new AuthToken();
        token.authorization = AUTHTOKEN;
        token.username = USER1;
        Assertions.assertDoesNotThrow(() -> authDao.Insert(token));
        Assertions.assertEquals(null,authDao.Find("notToken"));
    }
    @Test
    public void UserDaoCreateUserPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        Assertions.assertDoesNotThrow(() -> userDao.CreateUser(USER1, PASSWORD1, EMAIL1));
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
    }
    @Test
    public void UserDaoCreateUserFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        String user = null;
        String password = null;
        Assertions.assertThrows(DataAccessException.class,() -> userDao.CreateUser(user, PASSWORD1, EMAIL1));
        Assertions.assertEquals(401, userDao.FindUser(user, PASSWORD1));
        Assertions.assertThrows(DataAccessException.class,() -> userDao.CreateUser(USER1, password, EMAIL1));
        Assertions.assertEquals(401, userDao.FindUser(USER1, password));
    }
    @Test
    public void UserDaoClearTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.password = PASSWORD1;
        user.email = EMAIL1;
        userDao.Insert(user);
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
        Assertions.assertDoesNotThrow(() -> userDao.clear());
        Assertions.assertEquals(401, userDao.FindUser(USER1, PASSWORD1));
    }
    @Test
    public void UserDaoInsertPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.password = PASSWORD1;
        user.email = EMAIL1;
        Assertions.assertDoesNotThrow(() -> userDao.Insert(user));
        Assertions.assertEquals(200, userDao.FindUser(USER1, PASSWORD1));
    }
    @Test
    public void UserDaoInsertFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1 + "%^%$";
        user.password = PASSWORD1;
        user.email = EMAIL1;
        Assertions.assertThrows(DataAccessException.class,() -> userDao.Insert(user));
        Assertions.assertEquals(401, userDao.FindUser(USER1, PASSWORD1));
    }
    @Test
    public void UserDaoFindPassTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.password = PASSWORD1;
        user.email = EMAIL1;
        Assertions.assertDoesNotThrow(() -> userDao.Insert(user));
        Assertions.assertEquals(200, userDao.FindUser(USER1,PASSWORD1));
    }
    @Test
    public void UserDaoFindFailTest() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
        User user = new User();
        user.username = USER1;
        user.password = PASSWORD1;
        user.email = EMAIL1;
        Assertions.assertDoesNotThrow(() -> userDao.Insert(user));
        Assertions.assertEquals(401, userDao.FindUser(USER2,PASSWORD1));
        Assertions.assertEquals(401, userDao.FindUser(USER1,PASSWORD2));
    }
    @Test
    public void GameDaoInsertPassTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authorization = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertEquals(USER1,authDao.Find(authToken.authorization));
        Assertions.assertEquals(1, (gameDao.Find(game.gameID)).gameID);
    }
    @Test
    public void GameDaoInsertFailTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1 + "&^%$";
        game.blackUsername = USER2;
        game.game = chessgame;
        AuthToken authToken = new AuthToken();
        authToken.username = USER1;
        authToken.authorization = AUTHTOKEN;
        authDao.Insert(authToken);
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.Insert(game));
        Assertions.assertEquals(USER1,authDao.Find(authToken.authorization));
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.Find(game.gameID));
    }
    @Test
    public void GameDaoFindPassTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertEquals(USER1, gameDao.Find(1).whiteUsername);
        Assertions.assertEquals(USER2, gameDao.Find(1).blackUsername);
    }
    @Test
    public void GameDaoFindFailTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.Find(9));
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.Find(0));
    }
    @Test
    public void GameDaoFindAllPassTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        GameModel game2 = new GameModel();
        game2.gameID = 2;
        game2.whiteUsername = USER1;
        game2.blackUsername = USER2;
        game2.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game2));
        HashSet<GameModel> list = new HashSet<>();
        list.add(game);
        list.add(game2);
        Assertions.assertDoesNotThrow(() -> gameDao.FindAll());
        Assertions.assertEquals(list.size(), gameDao.FindAll().size());
    }
    @Test
    public void GameDaoFindAllTest2() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> gameDao.FindAll());
        Assertions.assertTrue(gameDao.FindAll().isEmpty());
    }
    @Test
    public void GameDaoUpdateGamePassTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.gameName = "Test";
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertEquals("Test",gameDao.Find(1).gameName);
        game.gameName = "newName";
        Assertions.assertDoesNotThrow(() -> gameDao.UpdateGame(1, game));
        Assertions.assertEquals("newName",gameDao.Find(1).gameName);
    }
    @Test
    public void GameDaoUpdateGameFailTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.gameName = "Test";
        game.whiteUsername = USER1;
        game.blackUsername = USER2;
        game.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertEquals("Test",gameDao.Find(1).gameName);
        game.gameName = "newName";
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.UpdateGame(3, game));
    }
    @Test
    public void GameDaoClaimSpotPassTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.gameName = "Test";
        game.whiteUsername = null;
        game.blackUsername = null;
        game.game = chessgame;
        userDao.CreateUser(USER1,PASSWORD1,EMAIL1);
        userDao.CreateUser(USER2,PASSWORD2,EMAIL2);
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertDoesNotThrow(() -> gameDao.claimSpot("BLACK", 1, USER1));
        Assertions.assertEquals(USER1,gameDao.Find(1).blackUsername);
        Assertions.assertDoesNotThrow(() -> gameDao.claimSpot("WHITE", 1, USER2));
        Assertions.assertEquals(USER2,gameDao.Find(1).whiteUsername);
    }
    @Test
    public void GameDaoClaimSpotFailTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.gameName = "Test";
        game.whiteUsername = null;
        game.blackUsername = null;
        game.game = chessgame;
        userDao.CreateUser(USER1,PASSWORD1,EMAIL1);
        userDao.CreateUser(USER2,PASSWORD2,EMAIL2);
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.claimSpot("BLACK", 9, USER1));
        Assertions.assertNull(gameDao.Find(1).blackUsername);
        Assertions.assertThrows(DataAccessException.class,() ->  gameDao.claimSpot("WHITE", -3, USER2));
        Assertions.assertNull(gameDao.Find(1).whiteUsername);
    }
    @Test
    public void gameDaoClearTest() throws DataAccessException {
        ChessGame chessgame = new game();
        ClearService clearService = new ClearService();
        clearService.clear();
        GameModel game = new GameModel();
        game.gameID = 1;
        game.gameName = "Test";
        game.whiteUsername = null;
        game.blackUsername = null;
        game.game = chessgame;
        Assertions.assertDoesNotThrow(() -> gameDao.Insert(game));
        Assertions.assertDoesNotThrow(() -> gameDao.Find(1));
        Assertions.assertDoesNotThrow(() -> gameDao.clear());
        Assertions.assertThrows(DataAccessException.class,() -> gameDao.Find(1));
    }
}
