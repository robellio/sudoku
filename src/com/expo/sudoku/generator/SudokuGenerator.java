package com.expo.sudoku.generator;

import com.expo.sudoku.enums.Difficulty;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final RandomGenerator random = RandomGenerator.getDefault();

    public static Grid generate(Difficulty difficulty) {
        int[][] solution = generateSolvedGrid();
        return createPuzzle(solution, difficulty.getCellsToRemove());
    }

    private static int[][] generateSolvedGrid() {
        int[][] grid = new int[SIZE][SIZE];
        SudokuSolver.solve(grid);
        return grid;
    }

    private static Grid createPuzzle(int[][] solution, int cellsToRemove) {
        int[][] puzzle = Arrays.stream(solution)
                .map(int[]::clone)
                .toArray(int[][]::new);

        int cellsRemoved = 0;
        while (cellsRemoved < cellsToRemove) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (puzzle[row][col] != 0) {
                puzzle[row][col] = 0;
                cellsRemoved++;
            }
        }

        return new Grid(puzzle);
    }
}