package com.expo.sudoku;

import com.expo.sudoku.ui.SudokuFrame;

import javax.swing.*;

public class SudokuGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame();
            frame.setVisible(true);
        });
    }

}
