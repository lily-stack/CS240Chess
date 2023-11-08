package DataAccess;

import chess.Board;
import chess.ChessGame;
import chess.ChessPiece;
import chess.Piece;
import com.google.gson.*;

import java.lang.reflect.Type;

public class BoardAdapter implements JsonDeserializer<Board>{
    public Board deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new PieceAdapter());
        return gsonBuilder.create().fromJson(jsonElement, Board.class);
    }
}
