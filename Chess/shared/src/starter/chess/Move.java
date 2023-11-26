package chess;

import java.util.Objects;

public class Move implements ChessMove{
    public ChessPosition startPosition;
    public ChessPosition endPosition;
    public ChessPiece.PieceType piece;

    @Override
    public ChessPosition getStartPosition() {
        return this.startPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return this.endPosition;
    }
    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return piece;
    }
    public void setStartPosition(ChessPosition start){
        this.startPosition = start;
    }
    public void setEndPosition(ChessPosition endPosition) {
        this.endPosition = endPosition;
    }
    public void setPromotion(ChessPiece.PieceType piece) {this.piece = piece;}
    public void setMove(ChessPosition start, ChessPosition endPosition, ChessPiece.PieceType piece){
        this.startPosition = start;
        this.endPosition = endPosition;
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(startPosition, move.startPosition) && Objects.equals(endPosition, move.endPosition) && piece == move.piece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, piece);
    }
}
