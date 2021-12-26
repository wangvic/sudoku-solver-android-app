package com.vicwang.sudokusolver;

import java.util.ArrayList;

public class Solver {

    int[][] board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;

    private int selectedRow;
    private int selectedColumn;

    public Solver() {
        selectedRow = -1;
        selectedColumn = -1;

        resetBoard();
    }

    public void getEmptyBoxIndices() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (this.board[r][c] == 0) {
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(r);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(c);
                }
            }
        }
    }

    private boolean validate(int row, int col) {
        if (this.board[row][col] > 0) {
            for (int i = 0; i < 9; i++) {
                if (this.board[i][col] == this.board[row][col] && row != i) {
                    return false;
                }
                if (this.board[row][i] == this.board[row][col] && col != i) {
                    return false;
                }
            }

            int boxRow = row / 3;
            int boxCol = col / 3;

            for (int r = boxRow * 3; r < boxRow * 3 + 3; r++) {
                for (int c = boxCol * 3; c < boxCol * 3 + 3; c++) {
                    if (this.board[r][c] == this.board[row][col] && row != r && col != c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean solve(SudokuBoard display) {
        int row = -1;
        int col = -1;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (this.board[r][c] == 0) {
                    row = r;
                    col = c;
                    break;
                }
            }
        }

        // no need to check col because it's always -1 if row equals -1
        if (row == -1) {
            return true;
        }

        for (int i = 1; i < 10; i++) {
            this.board[row][col] = i;
            display.invalidate();
            if (validate(row, col)) {
                if (solve(display)) {
                    return true;
                }
            }

            this.board[row][col] = 0;
        }
        return false;
    }

    public void resetBoard() {
        board = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }
        emptyBoxIndex = new ArrayList<>();
    }

    public void setNumberPos(int num) {
        if (this.selectedRow != -1 && this.selectedColumn != -1) {
            if (this.board[this.selectedRow - 1][this.selectedColumn - 1] == num) {
                this.board[this.selectedRow - 1][this.selectedColumn - 1] = 0;
            } else {
                this.board[this.selectedRow - 1][this.selectedColumn - 1] = num;
            }
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return emptyBoxIndex;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedColumn() {
        return this.selectedColumn;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }
}
