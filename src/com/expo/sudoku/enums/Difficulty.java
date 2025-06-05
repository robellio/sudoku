package com.expo.sudoku.enums;

public enum Difficulty {
    EASY(40),
    MEDIUM(50),
    HARD(60);

    private final int cellsToRemove;

    Difficulty(int cellsToRemove) {
        this.cellsToRemove = cellsToRemove;
    }

    public int getCellsToRemove() {
        return cellsToRemove;
    }
}
