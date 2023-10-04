package passoffTests;

import chess.*;

/**
 * Used for testing your code
 * Add in code using your classes for each method for each FIXME
 */
public class TestFactory {

    //Chess Functions
    //------------------------------------------------------------------------------------------------------------------
    public static ChessBoard getNewBoard(){
        return new Board();
    }

    public static ChessGame getNewGame(){
		return new game();
    }

    public static ChessPiece getNewPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type){
        ChessPiece piece = new Piece();
        piece.SetType(type);
        piece.SetColor(pieceColor);
		return piece;
    }

    public static ChessPosition getNewPosition(Integer row, Integer col){
        Position position = new Position();
        position.setRow(row - 1);
        position.setColumn(col - 1);
		return position;
    }

    public static ChessMove getNewMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece){
        ChessMove move = new Move();
        move.setStartPosition(startPosition);
        move.setEndPosition(endPosition);
        move.setPiece(promotionPiece);
		return move;
    }
    //------------------------------------------------------------------------------------------------------------------


    //Server API's
    //------------------------------------------------------------------------------------------------------------------
    public static String getServerPort(){
        return "8080";
    }
    //------------------------------------------------------------------------------------------------------------------


    //Websocket Tests
    //------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime(){
        /*
        Changing this will change how long tests will wait for the server to send messages.
        3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to change as you see fit,
        just know increasing it can make tests take longer to run.
        (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L;
    }
    //------------------------------------------------------------------------------------------------------------------
}
