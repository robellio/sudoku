package com.expo.sudoku.model;

public record Cell(int row, int col, int value, boolean isEditable) {

    public Cell withValue(int newValue) {
        return new Cell(row, col, newValue, isEditable);

    }

}
