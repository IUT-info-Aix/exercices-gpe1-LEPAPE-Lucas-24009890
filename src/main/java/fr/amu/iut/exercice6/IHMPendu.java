package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {

    private static final int MAX_VIES = 7;

    private int vies;
    private String motADeviner;
    private char[] lettresDevinees;
    private Label labelVies;
    private Label labelMot;
    private ImageView imagePendu;
    private Dico dico;
    private GridPane clavier;
    private Button boutonRejouer;

    @Override
    public void start(Stage primaryStage) {
        dico = new Dico();

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #d8fdfb;");

        imagePendu = new ImageView();
        imagePendu.setFitHeight(150);
        imagePendu.setPreserveRatio(true);

        labelVies = new Label();
        labelVies.setFont(Font.font("Arial", 18));

        labelMot = new Label();
        labelMot.setFont(Font.font("Courier New", 24));

        clavier = new GridPane();
        clavier.setHgap(5);
        clavier.setVgap(5);
        clavier.setAlignment(Pos.CENTER);

        boutonRejouer = new Button("Rejouer");
        boutonRejouer.setFont(Font.font("Arial", 14));
        boutonRejouer.setStyle("-fx-background-color: #bff0e9; -fx-border-color: #7fd9ce; -fx-border-radius: 10; -fx-background-radius: 10;");
        boutonRejouer.setOnAction(e -> demarrerNouvellePartie());

        root.getChildren().addAll(imagePendu, labelVies, labelMot, clavier, boutonRejouer);

        Scene scene = new Scene(root, 500, 550);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setScene(scene);
        primaryStage.show();

        demarrerNouvellePartie();
    }

    private void demarrerNouvellePartie() {
        vies = MAX_VIES;
        motADeviner = dico.getMot();
        lettresDevinees = new char[motADeviner.length()];
        for (int i = 0; i < lettresDevinees.length; i++) {
            lettresDevinees[i] = '*';
        }

        majAffichage();
        creerClavier();
    }

    private void creerClavier() {
        clavier.getChildren().clear();

        final int totalCols = 10;
        final String fondCouleur = "#d8fdfb"; // Couleur de fond de la fenêtre (tu peux ajuster cette valeur si nécessaire)
        final String bordureCouleur = "#FFA500"; // Bordure orange
        final int rayon = 8;  // Rayon des coins

        // 1. Voyelles centrées
        String voyelles = "aeiouy";
        int offset = (totalCols - voyelles.length()) / 2;  // (10−6)/2 = 2
        for (int i = 0; i < voyelles.length(); i++) {
            char lettre = voyelles.charAt(i);
            Button boutonLettre = new Button(String.valueOf(lettre));
            boutonLettre.setPrefSize(40, 40); // Rendre le bouton carré
            boutonLettre.setOnAction(e -> traiterLettre(lettre, boutonLettre));
            boutonLettre.setStyle(
                    "-fx-background-color: " + fondCouleur + ";" +
                            "-fx-border-color: " + bordureCouleur + ";" +
                            "-fx-border-width: 2;" +
                            "-fx-background-radius: " + rayon + ";" +
                            "-fx-border-radius: " + rayon + ";"
            );
            clavier.add(boutonLettre, offset + i, 0);
        }

        // 2. Autres lettres sur les deux lignes suivantes
        String autresLettres = "bcdfghjklmnpqrstvwxz";
        for (int i = 0; i < autresLettres.length(); i++) {
            char lettre = autresLettres.charAt(i);
            Button boutonLettre = new Button(String.valueOf(lettre));
            boutonLettre.setPrefSize(40, 40); // Rendre le bouton carré
            boutonLettre.setOnAction(e -> traiterLettre(lettre, boutonLettre));
            boutonLettre.setStyle(
                    "-fx-background-color: " + fondCouleur + ";" +
                            "-fx-border-color: " + bordureCouleur + ";" +
                            "-fx-border-width: 2;" +
                            "-fx-background-radius: " + rayon + ";" +
                            "-fx-border-radius: " + rayon + ";"
            );
            int col = i % totalCols;
            int row = (i / totalCols) + 1;
            clavier.add(boutonLettre, col, row);
        }
    }

    private void traiterLettre(char lettre, Button bouton) {
        bouton.setDisable(true);
        ArrayList<Integer> positions = dico.getPositions(lettre, motADeviner);

        if (positions.isEmpty()) {
            vies--;
        } else {
            for (int pos : positions) {
                lettresDevinees[pos] = lettre;
            }
        }

        majAffichage();

        if (vies == 0 || motTrouve()) {
            finDePartie();
        }
    }

    private void majAffichage() {
        labelVies.setText("Nombre de vies : " + vies);
        labelMot.setText(String.valueOf(lettresDevinees));
        String imagePath = "/exercice6/pendu" + (vies == 0 && !motTrouve() ? 7 : MAX_VIES - vies) + ".png";
        if (vies == 0 && motTrouve()) imagePath = "/exercice6/penduWin.png";
        imagePendu.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }

    private boolean motTrouve() {
        return String.valueOf(lettresDevinees).equals(motADeviner);
    }

    private void finDePartie() {
        for (javafx.scene.Node node : clavier.getChildren()) {
            node.setDisable(true);
        }
        if (motTrouve()) {
            labelVies.setText("Bravo ! Mot trouvé : " + motADeviner);
        } else {
            labelVies.setText("Perdu ! Le mot était : " + motADeviner);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
