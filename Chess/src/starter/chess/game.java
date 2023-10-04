package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class game implements ChessGame{
    public TeamColor color;
    public ChessBoard board = new Board();
    @Override
    public TeamColor getTeamTurn() {
        return color;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        color = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
        Collection<ChessMove> possibleMoves = new HashSet<>();
        ChessPosition possiblePos = new Position();
        ChessMove aMove = new Move();
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK) {

            possiblePos.setColumn(col);
            possiblePos.setRow(row);

            for (int left = startPosition.getColumn() - 1; left >= 0; left--) {
                possiblePos.setColumn(left);
                if (board.getPiece(possiblePos) != null) {
                    break;
                } else {
                    aMove.setStartPosition(startPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            possiblePos.setColumn(col);
            possiblePos.setRow(row);
            for (int right = startPosition.getColumn() + 1; right < 8; right++) {
                possiblePos.setColumn(right);
                if (board.getPiece(possiblePos) != null) {
                    break;
                } else {
                    aMove.setStartPosition(startPosition);
                    aMove.setPromotion(null);
                    aMove.setEndPosition(possiblePos);
                    possibleMoves.add(aMove);
                }
            }
            possiblePos.setColumn(col);
            possiblePos.setRow(row);
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
            possiblePos.setColumn(col);
            possiblePos.setRow(row);
            for (int down = startPosition.getRow() - 1; down < 8; down++) {
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
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){
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
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){
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
            }
        }
        return possibleMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition endPos = move.getEndPosition();
        ChessPosition startPos = move.getStartPosition();
        if(board.getPiece(endPos) == null){
            board.addPiece(endPos, board.getPiece(startPos));
            board.deletePiece(startPos);
        }
        else{
            throw new InvalidMoveException();
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        return false;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }

    @Override
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }


}
