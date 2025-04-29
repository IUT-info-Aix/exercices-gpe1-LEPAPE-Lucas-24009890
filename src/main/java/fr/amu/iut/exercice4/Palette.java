package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du label
        label = new Label("Cliquez sur un bouton");
        label.setAlignment(Pos.CENTER);

        // Création du panneau central
        panneau = new Pane();
        panneau.setStyle("-fx-background-color: white;");

        // Création des boutons
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        // Action sur les boutons
        vert.setOnAction(e -> {
            nbVert++;
            panneau.setStyle("-fx-background-color: green;");
            label.setText("Vert cliqué " + nbVert + " fois");
        });

        rouge.setOnAction(e -> {
            nbRouge++;
            panneau.setStyle("-fx-background-color: red;");
            label.setText("Rouge cliqué " + nbRouge + " fois");
        });

        bleu.setOnAction(e -> {
            nbBleu++;
            panneau.setStyle("-fx-background-color: blue;");
            label.setText("Bleu cliqué " + nbBleu + " fois");
        });

        // Création de la HBox contenant les boutons
        bas = new HBox(10, vert, rouge, bleu);
        bas.setAlignment(Pos.CENTER);

        // Création du BorderPane
        root = new BorderPane();
        root.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        root.setCenter(panneau);
        root.setBottom(bas);

        // Création de la scène
        Scene scene = new Scene(root, 400, 200);

        // Configuration de la fenêtre
        primaryStage.setTitle("Palette de Couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

