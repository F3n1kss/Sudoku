package com.sudoku;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Random;

public class HelloController  {
    private Label[][] labels;
    private static final int GRID_SIZE = 9;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label;
    @FXML
    private MenuBar menuBar;

    // Game Menu
    @FXML
    private Menu gameMenu;
    @FXML
    private MenuItem newGameItem;
    @FXML
    private MenuItem leaderBoardItem;
    @FXML
    private MenuItem preferencesItem;
    @FXML
    private MenuItem quitItem;

    // Edit Menu
    @FXML
    private Menu editMenu;
    @FXML
    private Menu editDifficultItem;
    @FXML
    private MenuItem easy;
    @FXML
    private MenuItem medium;
    @FXML
    private MenuItem hard;
    @FXML
    private MenuItem createCustomDifficultItem;
    @FXML
    private MenuItem pasteItem;

    // Help Menu
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem rulesItem;

    @FXML
    private void handleNewGame(ActionEvent event) {
        fillGridPaneWithRandomValues();
        printLabelsArray();
    }
    private void clearLabels() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Очищаємо текст у лейбла
                labels[row][col].setText("");
            }
        }
    }
    public void fillGridPaneWithRandomValues() {


        Random random = new Random();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                labels[row][col] = new Label();
                labels[row][col].setMinSize(39, 39);

                // Генеруємо випадкове число від 0 до 9 і встановлюємо його як текст лейбла
                int randomValue = random.nextInt(9)+1;
                labels[row][col].setText(String.valueOf(randomValue));
                labels[row][col].setFont(new Font(18));
                // Додаємо лейбл на сітку
                gridPane.add(labels[row][col], col, row);
                labels[row][col].setStyle("-fx-padding: 0 0 0 7;");
            }
        }
    }

    public void printLabelsArray() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(labels[row][col].getText() + "\t");
            }
            System.out.println(); // Перехід на новий рядок після кожного рядка масиву
        }
    }

    @FXML
    private void handleLeaderBoard(ActionEvent event) {

    }

    @FXML
    private void handlePreferences(ActionEvent event) {

    }

    @FXML
    private void handleQuit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleEditDifficult(ActionEvent event) {

    }

    @FXML
    private void handleCreateCustomDifficult(ActionEvent event) {

    }

    @FXML
    private void handleRules(ActionEvent event) throws IOException {
        openWindow("Rules.fxml", "Rules");
    }


    private void openWindow(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.setTitle(title);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.show();
    }



}