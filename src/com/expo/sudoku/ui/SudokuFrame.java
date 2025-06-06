package com.expo.sudoku.ui;

import com.expo.sudoku.enums.Difficulty;
import com.expo.sudoku.generator.SudokuGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SudokuFrame extends JFrame {
    private Grid grid;

    public SudokuFrame() {
        setTitle("Sudoku Java 21");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeMenu();
        startNewGame(Difficulty.MEDIUM);

        pack();
        setLocationRelativeTo(null);

    }

    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Jogo");
        JMenuItem newGameItem = new JMenuItem("Novo Jogo");
        newGameItem.addActionListener(this::newGameAction);

        JMenu difficultyMenu = new JMenu("Dificuldade");
        for (Difficulty difficulty : Difficulty.values()) {
            JMenuItem item = new JMenuItem(difficulty.name());
            item.addActionListener(e -> startNewGame(difficulty));
            difficultyMenu.add(item);
        }

        gameMenu.add(newGameItem);
        gameMenu.add(difficultyMenu);
        menuBar.add(gameMenu);

        setJMenuBar(menuBar);

    }

    private void newGameAction(ActionEvent e) {
        startNewGame(Difficulty.MEDIUM);
    }

    private void startNewGame(Difficulty difficulty) {
        grid = SudokuGenerator.generate(difficulty);
        getContentPane().removeAll();
        add(new SudokuPanel(grid), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}
