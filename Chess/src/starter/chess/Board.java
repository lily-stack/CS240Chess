package chess;

public class Board implements ChessBoard{
    int BOARD_LENGTH = 8;
    int BOARD_WIDTH = 8;
    private ChessPiece[][] theBoard = new ChessPiece[BOARD_WIDTH][BOARD_LENGTH];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int column = position.getColumn();
        int row = position.getRow();
        theBoard[row][column] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow();
        int column = position.getColumn();
        return theBoard[row][column];
    }

    @Override
    public void resetBoard() {
        for(int i = 0; i < BOARD_WIDTH; i++) {
            for(int j = 0; j < BOARD_LENGTH; j++){
                Position thePosition = new Position();
                if(i < 2){
                    // thePiece.setColor
                    theBoard[i][j].SetColor(ChessGame.TeamColor.WHITE);
                }
                if(i > 5){
                    // thePiece.setColor
                    theBoard[i][j].SetColor(ChessGame.TeamColor.BLACK);
                }
                if(i == 1 || i == 6) {
                    thePosition.row = i;
                    thePosition.column = j;
                    //pawn enum
                    theBoard[i][j].SetType(ChessPiece.PieceType.PAWN);
                    addPiece(thePosition,theBoard[i][j]);
                }
                if(i == 0 || i == 7){
                    if(j == 0 || j == 7){
                        //castle
                        theBoard[i][j].SetType(ChessPiece.PieceType.ROOK);
                    }
                    if(j == 1 || j == 6){
                        //knight
                        theBoard[i][j].SetType(ChessPiece.PieceType.KNIGHT);
                    }
                    if(j == 2 || j == 5){
                        //bishop
                        theBoard[i][j].SetType(ChessPiece.PieceType.BISHOP);
                    }
                    if(j == 3) {
                        //king
                        theBoard[i][j].SetType(ChessPiece.PieceType.KING);
                    }
                    if(j == 4){
                        //queen
                        theBoard[i][j].SetType(ChessPiece.PieceType.QUEEN);
                    }
                }
            }
        }
    }
}
