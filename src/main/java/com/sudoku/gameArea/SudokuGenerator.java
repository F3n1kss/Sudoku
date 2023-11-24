package com.sudoku.gameArea;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.*;

public class SudokuGenerator {
    List<GameArea> coordinates = new ArrayList<>();
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private static final int GRID_SIZE = 9;


    public void fillGridPaneWithRandomValues(GridPane gridPane, int difficult) {
        generateNumbers(difficult);
        Random random = new Random();
        for (GameArea area : coordinates) {
            int row = area.getRow();
            int col = area.getCol();

            TextField textField = new TextField();
            textField.setMinSize(30, 30);
            textField.setFont(new Font(18));
            textField.setStyle("-fx-border-color: black;");
            textField.setStyle("-fx-border-color: black;");
            textField.setStyle("-fx-background-color: transparent;");
            textField.setStyle("-fx-text-fill: black;");

            int randomValue = random.nextInt(9) + 1;
            textField.setText(String.valueOf(randomValue));
            // Заблокировать возможность редактирования для неизменных ячеек
            textField.setEditable(false);
            // Установить прозрачный фон
            textField.setAlignment(Pos.CENTER);
            textField.setStyle("-fx-cursor: default;");

            gridPane.add(textField, col, row);
            textFields[row][col] = textField;
        }
    }

    public void generateNumbers(int difficult) {
        Random random = new Random();
        while (coordinates.size() < difficult) {
                int rows = random.nextInt(GRID_SIZE);
                int cols = random.nextInt(GRID_SIZE);
                GameArea area = new GameArea(rows, cols);

                if (!coordinates.contains(area)) {
                    coordinates.add(area);
                }
                else {
                    rows = random.nextInt(GRID_SIZE);
                    cols = random.nextInt(GRID_SIZE);
                }
        }
    }
}