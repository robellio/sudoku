package com.expo.sudoku.model;

import java.util.Arrays;

public class Grid {

    private static final int SIZE = 9;
    private static final int BOX_SIZE = 3;
    private final Cell[][] cells;

    public Grid(int[][] puzzle) {
        this.cells = new Cell[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                boolean editable = puzzle[row][col] == 0;
                cells[row][col] = new Cell(row, col, puzzle[row][col], editable);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCellValue(int row, int col, int value) {
        if (cells[row][col].isEditable()) {
            cells[row][col] = cells[row][col].withValue(value);
        }
    }

    public boolean isValidMove(int row, int col, int value) {
        return value == 0 || (isValidInRow(row, value)
                && isValidInCol(col, value)
                && isValidInBox(row - row % BOX_SIZE, col - col % BOX_SIZE, value));
    }

    private boolean isValidInRow(int row, int value) {
        return Arrays.stream(cells[row]).noneMatch(cell -> cell.value() == value);
    }

    private boolean isValidInCol(int col, int value) {
        return Arrays.stream(cells)
                .noneMatch(rowCells -> rowCells[col].value() == value);
    }

    private boolean isValidInBox(int boxStartRow, int boxStartCol, int value) {
        for (int row = 0; row < BOX_SIZE; row++) {
            for (int col = 0; col < BOX_SIZE; col++) {
                if (cells[row + boxStartRow][col + boxStartCol].value() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .noneMatch(cell -> cell.value() == 0);
    }

    public int[][] toArray() {
        int[][] array = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                array[row][col] = cells[row][col].value();
            }
        }
        return array;
    }
}
