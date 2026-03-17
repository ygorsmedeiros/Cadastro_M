package com.ygor.cadastromestre;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private VBox sidebar;
    private boolean estendida = true;

    /**
     * Retorno da filtragem do ComboBom1
     */
    @FXML
    private Label Camp1;

    @FXML
    private Label Camp2;

    @FXML
    private Label Camp3;

    @FXML
    private ComboBox<String> myComboBox1;

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

    @FXML
    private void fecharSidebar() {
        if (estendida) {
            toggleSidebar(); // Só chama a animação se ela estiver aberta
        }
    }

    @FXML
    private ComboBox<String>  myComboBox2, myComboBox3,
            myComboBox4, myComboBox5, myComboBox6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] opcoes = {"GIM"};

        ObservableList<String> listaDeOpcoes = FXCollections.observableArrayList(opcoes);

        List<ComboBox<String>> todosOsCombos = Arrays.asList(
                myComboBox1, myComboBox2, myComboBox3,
                myComboBox4, myComboBox5, myComboBox6
        );

        for (ComboBox<String> combo : todosOsCombos) {
            if (combo != null) {
                combo.setItems(listaDeOpcoes);
            }
        }

        myComboBox1.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {

            if (valorNovo != null) {

                // Verifica se a seleção foi "BO"
                if (valorNovo.equals("GIM")) {
                    Camp1.setText("William");
                    Camp2.setText("Paulo Cesar");
                } else {
                    Camp1.setText("William");
                    Camp2.setText("Paulo Cesar");
                }
            }
        });
    }
}