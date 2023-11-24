package com.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    private static final String ICON_PATH = "Icon.png";
    private static final String WINDOW_TITLE = "SU-DO-KU";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image(ICON_PATH);
        stage.getIcons().add(image);
        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}