package com.sudoku.gameArea;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private static final int GRID_SIZE = 9;


    public void fillGridPaneWithRandomValues(GridPane gridPane, int difficult) {


        // Список індексів для випадкового вибору
        List<Integer> availableIndexes = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            availableIndexes.add(i);
        }

        // Вибираємо 5 випадкових індексів
        Collections.shuffle(availableIndexes);
        List<Integer> selectedIndexes = availableIndexes.subList(0, difficult);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                TextField textField = new TextField();
                textField.setMinSize(30, 30);
                textField.setFont(new Font(18));
                textField.setStyle("-fx-border-color: black;");
                textField.setStyle("-fx-border-color: black;");
                textField.setStyle("-fx-background-color: transparent;");
                textField.setStyle("-fx-text-fill: black;");

                // Заповнення значенням тільки для вибраних кліток
                if (selectedIndexes.contains(row * GRID_SIZE + col)) {
                    int randomValue = (row * GRID_SIZE + col) % GRID_SIZE + 1;
                    textField.setText(String.valueOf(randomValue));
                    // Заблокувати можливість редагування для незмінних кліток
                    textField.setEditable(false);
                    // Встановити прозорий фон
                    textField.setAlignment(Pos.CENTER);
                    textField.setStyle("-fx-cursor: default;");
                }

                gridPane.add(textField, col, row);
                textFields[row][col] = textField;
            }
        }
    }
}
