package com.sudoku.gameArea;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.*;

public class AreaGenerator {
    private static final int GRID_SIZE = 9;

    private List<GameArea> initialCoordinates;
    private List<GameArea> coordinates;
    private List<GameArea> centers;
    private TextField[][] textFields;

    public AreaGenerator() {
        initialCoordinates = new ArrayList<>();
        coordinates = new ArrayList<>();
        centers = new ArrayList<>();
        textFields = new TextField[GRID_SIZE][GRID_SIZE];
    }

    public void fillGridPaneWithRandomValues(GridPane gridPane, int difficulty) {
        generateSudoku(difficulty);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                GameArea area = initialCoordinates.get(row * GRID_SIZE + col);
                int value = area.getValue();

                TextField textField = new TextField();
                textField.setMinSize(30, 30);
                textField.setFont(new Font(18));
                textField.setStyle("-fx-border-color: black;");
                textField.setStyle("-fx-background-color: transparent;");
                textField.setStyle("-fx-text-fill: black;");
                textField.setText(value > 0 ? String.valueOf(value) : "");

                // Устанавливаем редактируемость в зависимости от наличия значения
                textField.setEditable(value == 0);

                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-cursor: " + (value == 0 ? "text" : "default"));

                gridPane.add(textField, col, row);
                textFields[row][col] = textField;
            }
        }
    }

    private void generateSudoku(int difficulty) {
        initialCoordinates.clear();
        coordinates.clear();

        solveSudoku();
        initialCoordinates.addAll(coordinates);
        removeNumbers(difficulty);
        shuffleFirstRow();
    }

    private void solveSudoku() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                GameArea area = new GameArea(row, col);
                coordinates.add(area);
            }
        }
        solve(coordinates);
    }

    private void shuffleFirstRow() {
        List<Integer> firstRowValues = new ArrayList<>();
        for (int col = 0; col < GRID_SIZE; col++) {
            firstRowValues.add(coordinates.get(col).getValue());
        }

        Collections.shuffle(firstRowValues);

        for (int col = 0; col < GRID_SIZE; col++) {
            coordinates.get(col).setValue(firstRowValues.get(col));
        }
    }

    private boolean solve(List<GameArea> board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int index = i * GRID_SIZE + j;
                GameArea area = board.get(index);
                if (area.getValue() == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, i, j, num)) {
                            area.setValue(num);
                            if (solve(board)) {
                                return true;
                            }
                            area.setValue(0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(List<GameArea> board, int row, int col, int num) {
        for (int i = 0; i < GRID_SIZE; i++) {
            int rowIndex = row * GRID_SIZE + i;
            int colIndex = i * GRID_SIZE + col;
            int squareIndex = 3 * (row / 3) * GRID_SIZE + 3 * (col / 3) + i % 3 + i / 3 * GRID_SIZE;

            if (board.get(rowIndex).getValue() == num || board.get(colIndex).getValue() == num ||
                    board.get(squareIndex).getValue() == num) {
                return false;
            }
        }
        return true;
    }

    private void removeNumbers(int difficulty) {
        Random random = new Random();
        Set<Integer> removedIndices = new HashSet<>();

        while (removedIndices.size() < 81 - difficulty) {
            int randomIndex = random.nextInt(GRID_SIZE * GRID_SIZE);
            removedIndices.add(randomIndex);
            initialCoordinates.get(randomIndex).setValue(0);
        }
    }
}
