package com.sudoku.gameArea;

public class GameArea {
    private int row;
    private int col;

    public GameArea(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
