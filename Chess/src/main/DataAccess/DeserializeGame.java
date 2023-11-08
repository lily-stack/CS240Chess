package DataAccess;

import chess.*;
import com.google.gson.GsonBuilder;

public class DeserializeGame {
    public ChessGame deserializeGame(String gameString){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new PieceAdapter());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new BoardAdapter());
        return gsonBuilder.create().fromJson(gameString, game.class);
    }
}
