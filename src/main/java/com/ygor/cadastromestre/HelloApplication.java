package com.ygor.cadastromestre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1650, 910);
        stage.setTitle("Novo Cadastro Mestre!");
        stage.setMaxHeight(910);
        stage.setMaxWidth(1670);
        stage.setMinHeight(810);
        stage.setMinWidth(700);
        stage.setScene(scene);

        stage.widthProperty().addListener((obs, antigo, novo) -> {
            stage.centerOnScreen();
        });

        stage.heightProperty().addListener((obs, antigo, novo) -> {
            stage.centerOnScreen();
        });

        stage.iconifiedProperty().addListener((obs, antigo, novo) -> {
            if (!novo) stage.centerOnScreen();
        });

        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}