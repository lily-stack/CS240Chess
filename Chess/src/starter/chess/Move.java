package chess;

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
    public void setPiece(ChessPiece.PieceType piece) {
        this.piece = piece;
    }
}
