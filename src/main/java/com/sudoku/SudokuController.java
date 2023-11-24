package com.sudoku;

import com.sudoku.gameArea.SudokuGenerator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SudokuController {
    SudokuGenerator generator = new SudokuGenerator();

    private TextField[][] textFields;
    private static final int GRID_SIZE = 9;

    //Константи для рівнів складності
    private int difficult = EASY_DIFFICULTY;
    private static final int EASY_DIFFICULTY = 30;
    private static final int MEDIUM_DIFFICULTY = 20;
    private static final int HARD_DIFFICULTY = 10;
    @FXML
    private ToggleGroup toggleGroup;
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
    private RadioMenuItem easy;
    @FXML
    private RadioMenuItem medium;
    @FXML
    private RadioMenuItem hard;
    @FXML
    private MenuItem createCustomDifficultItem;
    @FXML
    private MenuItem pasteItem;

    // Help Menu
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem rulesItem;

    //Кнопки для меню Game
    @FXML
    private void handleNewGame(ActionEvent event) {
        generator.fillGridPaneWithRandomValues(gridPane, difficult);
        System.out.println(difficult);

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


    //Кнопки для меню Edit
    @FXML
    private void handleEditDifficult(ActionEvent event) {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();

        if (selectedToggle != null) {
            RadioMenuItem selectedRadioMenuItem = (RadioMenuItem) selectedToggle;

            if (selectedRadioMenuItem.getId().equals(easy.getId())) {
                difficult = EASY_DIFFICULTY;
            } else if (selectedRadioMenuItem.getId().equals(medium.getId())) {
                difficult = MEDIUM_DIFFICULTY;
            } else if (selectedRadioMenuItem.getId().equals(hard.getId())) {
                difficult = HARD_DIFFICULTY;
            }
        }
    }

    @FXML
    private void handleCreateCustomDifficult(ActionEvent event) {

    }

    //Кнопки для меню Help
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