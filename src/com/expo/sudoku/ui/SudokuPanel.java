package com.expo.sudoku.ui;

import com.expo.sudoku.model.Grid;
import com.expo.sudoku.model.Cell;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SudokuPanel extends JPanel{

    private static final int SIZE = 9;
    private static final int CELL_SIZE = 60;
    private final Grid grid;

    public SudokuPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(CELL_SIZE * SIZE, CELL_SIZE * SIZE));
        setLayout(new GridLayout(SIZE, SIZE));
        initializeCells();
    }

    private void initializeCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = grid.getCell(row, col);
                JTextField textField = createTextField(cell);
                add(textField);
            }
        }
    }

    private JTextField createTextField(Cell cell) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("SansSerif", Font.BOLD, 20));

        if (cell.value() != 0) {
            textField.setText(String.valueOf(cell.value()));
        }

        textField.setEditable(cell.isEditable());
        textField.setBackground(cell.isEditable() ? Color.WHITE : Color.LIGHT_GRAY);

        if (cell.value() != 0) {
            textField.setText(String.valueOf(cell.value()));
        }
        textField.setEditable(cell.isEditable());
        textField.setBackground(cell.isEditable() ? Color.WHITE : Color.LIGHT_GRAY);

        if (cell.isEditable()) {
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) || c == '0') {
                        e.consume();
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                int value = Integer.parseInt(textField.getText() + c);
                                if (grid.isValidMove(cell.row(), cell.col(), value)) {
                                    grid.setCellValue(cell.row(), cell.col(), value);
                                    if (grid.isComplete()) {
                                        JOptionPane.showMessageDialog(SudokuPanel.this,
                                                "Parabéns! Você resolveu o Sudoku!",
                                                "Vitória",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    textField.setText("");
                                    JOptionPane.showMessageDialog(SudokuPanel.this,
                                            "Movimento inválido!",
                                            "Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                textField.setText("");
                            }
                        });
                    }
                }
            });
        }

        if ((cell.row() / 3) % 2 == (cell.col() / 3) % 2) {
            textField.setBackground(textField.getBackground().darker());
        }

        return textField;

    }
}
