package com.example.partie4;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class Calculator extends Pane {

    public Calculator() throws IOException {

        // chargement des composants de la fenêtre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calculator.fxml"));
        VBox root = loader.load();

        // Ajout des composants dans la fenêtre
        this.getChildren().add(root);
    }
}
