package chess;

import java.util.Collection;
import java.util.HashSet;

public class Piece implements ChessPiece{
    public PieceType type = null;
    public ChessGame.TeamColor color = null;
    //setposition(int r,int c)
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    public void SetType(PieceType t){
        type = t;
    }
    public void SetColor(ChessGame.TeamColor c) {
        color = c;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Collection<ChessMove> possibleMoves = new HashSet<>();

        //ChessMove aMove = new Move();
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK) {
            for (int left = myPosition.getColumn() - 1; left >= 0; left--) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(left);
                if(board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                }
                else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
                }
            }

            for (int right = myPosition.getColumn() + 1; right < 8; right++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(row);
                possiblePos.setColumn(right);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                }
                else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int up = myPosition.getRow() + 1; up < 8; up++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setColumn(col);
                possiblePos.setRow(up);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                } else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            for (int down = myPosition.getRow() - 1; down < 8; down++) {
                ChessPosition possiblePos = new Position();
                possiblePos.setRow(down);
                possiblePos.setColumn(col);
                if (board.getPiece(possiblePos) != null) {
                    if (board.getPiece(possiblePos).getTeamColor() != color) {
                        ChessMove aMove = new Move();
                        aMove.setStartPosition(myPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                    break;
                }
                else {
                    ChessMove aMove = new Move();
                    aMove.setStartPosition(myPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){
            /*
            possiblePos.setColumn(col);
            possiblePos.setRow(row);
            for(int left = startPosition.getColumn() - 1; left >= 0; left--) {
                possiblePos.setColumn(left);
                //up left diagnal
                for(int up = startPosition.getRow() + 1; up < 8; up++){
                    possiblePos.setRow(up);
                    if (board.getPiece(possiblePos) != null) {
                        break;
                    }
                    else {
                        aMove.setStartPosition(startPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                }
                //down left diagnal
                for(int down = startPosition.getRow() - 1; down >= 0; down--){
                    possiblePos.setRow(down);
                    if (board.getPiece(possiblePos) != null) {
                        break;
                    }
                    else {
                        aMove.setStartPosition(startPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                }
            }
            for(int right = startPosition.getColumn() + 1; right < 8; right++) {
                possiblePos.setColumn(right);
                //up left diagnal
                for (int up = startPosition.getRow() + 1; up < 8; up++) {
                    possiblePos.setRow(up);
                    if (board.getPiece(possiblePos) != null) {
                        break;
                    } else {
                        aMove.setStartPosition(startPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                }
                //down left diagnal
                for (int down = startPosition.getRow() - 1; down >= 0; down--) {
                    possiblePos.setRow(down);
                    if (board.getPiece(possiblePos) != null) {
                        break;
                    } else {
                        aMove.setStartPosition(startPosition);
                        aMove.setPromotion(null);
                        aMove.setEndPosition(possiblePos);
                        possibleMoves.add(aMove);
                    }
                }
            }*/
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){
        /*
            possiblePos.setColumn(col + 1);
            possiblePos.setRow(row);
            if(board.getPiece(possiblePos) == null){
                aMove.setStartPosition(startPosition);
                aMove.setPromotion(null);
                aMove.setEndPosition(possiblePos);
                possibleMoves.add(aMove);
            }
            possiblePos.setColumn(col - 1);
            possiblePos.setRow(row);
            if(board.getPiece(possiblePos) == null){
                aMove.setStartPosition(startPosition);
                aMove.setPromotion(null);
                aMove.setEndPosition(possiblePos);
                possibleMoves.add(aMove);
            }
            possiblePos.setColumn(col);
            possiblePos.setRow(row + 1);
            if(board.getPiece(possiblePos) == null){
                aMove.setStartPosition(startPosition);
                aMove.setPromotion(null);
                aMove.setEndPosition(possiblePos);
                possibleMoves.add(aMove);
            }
            possiblePos.setColumn(col);
            possiblePos.setRow(row - 1);
            if(board.getPiece(possiblePos) == null){
                aMove.setStartPosition(startPosition);
                aMove.setPromotion(null);
                aMove.setEndPosition(possiblePos);
                possibleMoves.add(aMove);
            }*/
        }
        return possibleMoves;
    }
}
