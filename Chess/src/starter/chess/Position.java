package chess;

public class Position implements ChessPosition{
    int row = 0;
    int column = 0;
    @Override
    public int getRow() {
        return row;
    }
    @Override
    public int getColumn() {
        return column;
    }
}
