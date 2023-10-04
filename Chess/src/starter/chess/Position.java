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
    public void setRow(int rowNum){
        row = rowNum;
    }
    public void setColumn(int colNum){
        column = colNum;
    }
}
