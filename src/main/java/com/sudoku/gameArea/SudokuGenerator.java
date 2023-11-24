package com.sudoku.gameArea;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.*;

public class SudokuGenerator {
    List<GameArea> coordinates = new ArrayList<>();
    List<GameArea> centers = new ArrayList<>();
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private static final int GRID_SIZE = 9;


    public void fillGridPaneWithRandomValues(GridPane gridPane, int difficult) {
        Random random = new Random();
        int randomValue = random.nextInt(9) + 1;
        generateCenters();
        isValidSquare(randomValue);
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

    public boolean isValidSquare(int randomValue) {
        boolean isValidSquare = true;
        for (GameArea center : centers)
        {
            for(int i = center.getCol()-1; i < center.getCol()+1; i++)
            {
                for (int b = center.getRow()-1; b < center.getCol()+1; b++)
                {
                    if (randomValue == center.getValue())
                    {
                        isValidSquare = false;
                        break;
                    }
                }
            }
        }
        return isValidSquare;
    }

    public void generateCenters() {
        int x = 1;
        int y = 1;
        GameArea area = new GameArea(x, y);
        for (x = 1; x <= 7; x = x + 3)
        {
            for (y = 1; y <= 7; y = y + 3)
            {
                area = new GameArea(x, y);
                centers.add(area);
                System.out.println("X: " + x + " Y:" + y);
            }
        }
    }

//        while (coordinates.size() < difficult) {
//                int rows = random.nextInt(GRID_SIZE);
//                int cols = random.nextInt(GRID_SIZE);
//                GameArea area = new GameArea(rows, cols);
//
//                if (!coordinates.contains(area)) {
//                    coordinates.add(area);
//                }
//                else {
//                    rows = random.nextInt(GRID_SIZE);
//                    cols = random.nextInt(GRID_SIZE);
//                }
//        }
}