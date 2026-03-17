package com.ygor.cadastromestre.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SideController {

    // O ID deve ser exatamente igual ao fx:id do FXML
    @FXML private VBox sidebar;

    private boolean estendida = true;

    // Este é o método que o botão "Menu" vai chamar
    @FXML
    private void toggleSidebar() {
        double larguraFinal = estendida ? 70.0 : 200.0;

        Timeline anima = new Timeline();
        KeyValue kv = new KeyValue(sidebar.prefWidthProperty(), larguraFinal);
        KeyFrame kf = new KeyFrame(Duration.millis(300), kv);

        anima.getKeyFrames().add(kf);
        anima.play();

        estendida = !estendida;
    }
}