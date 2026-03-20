package com.ygor.cadastromestre.Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DescriptorSigla {

    @FXML
    private Button btnFechar;

    @FXML
    private void fecharJanela() {
        Node sidebarRoot = btnFechar.getScene().lookup("#rootSidebar");

        BorderPane mainBorderPane = (BorderPane) btnFechar.getScene().lookup("#Bord_root_pane");

        if (sidebarRoot != null && mainBorderPane != null) {
            TranslateTransition exit = new TranslateTransition(Duration.millis(600), sidebarRoot);
            exit.setToX(600);
            exit.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);

            exit.setOnFinished(e -> {
                mainBorderPane.setRight(null);
            });

            exit.play();
        } else {
            if (mainBorderPane != null) mainBorderPane.setRight(null);
        }
    }
}