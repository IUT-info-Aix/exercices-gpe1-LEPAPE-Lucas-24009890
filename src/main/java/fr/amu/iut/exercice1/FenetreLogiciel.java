package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création du conteneur principal
        BorderPane canvas = new BorderPane();
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        // Création des déroulants
        MenuBar menuBar = new MenuBar();

        // Création du menu "File"
        Menu menuFile = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        menuFile.getItems().addAll(newItem, openItem, saveItem);

        // Création du menu "Edit"
        Menu menuEdit = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        menuEdit.getItems().addAll(cutItem, copyItem, pasteItem);

        // Création du menu "Help"
        Menu menuHelp = new Menu("Help");

        // Ajout des menus à la barre de menus
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);

        // Création des boutons
        VBox bareDeBoutons = new VBox();
        bareDeBoutons.setAlignment(Pos.CENTER);

        Label labelBouton = new Label("Boutons :");
        Button bouton1 = new Button("Bouton 1");
        Button bouton2 = new Button("Bouton 2");
        Button bouton3 = new Button("Bouton 3");

        VBox.setMargin(labelBouton, new Insets(5));
        VBox.setMargin(bouton1, new Insets(5));
        VBox.setMargin(bouton2, new Insets(5));
        VBox.setMargin(bouton3, new Insets(5));

        bareDeBoutons.getChildren().addAll(labelBouton, bouton1, bouton2, bouton3);

        // Création du formulaire
        GridPane formulaire = new GridPane();
        formulaire.setAlignment(Pos.CENTER);
        formulaire.setHgap(10);
        formulaire.setVgap(20);

        Label labelName = new Label("Name:");
        TextField textName = new TextField();
        Label labelEmail = new Label("Email:");
        TextField textEmail = new TextField();
        Label labelPassword = new Label("Password:");
        PasswordField textPassword = new PasswordField();

        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");

        // Ajout des éléments au GridPane
        formulaire.add(labelName, 0, 0);
        formulaire.add(textName, 1, 0);
        formulaire.add(labelEmail, 0, 1);
        formulaire.add(textEmail, 1, 1);
        formulaire.add(labelPassword, 0, 2);
        formulaire.add(textPassword, 1, 2);

        // Création d'un HBox pour centrer les boutons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(submitButton, cancelButton);

        // Ajout de l'HBox contenant les boutons au GridPane
        formulaire.add(buttonBox, 0, 3, 2, 1);

        // Ajout d'un label en bas de la fenêtre
        Label labelBas = new Label("Ceci est un label de bas de page");
        HBox BLabelBas = new HBox();
        BLabelBas.setAlignment(Pos.CENTER);
        BLabelBas.getChildren().addAll(labelBas);

        // Placement des différents
        canvas.setTop(menuBar);
        canvas.setLeft(bareDeBoutons);
        canvas.setCenter(formulaire);
        canvas.setBottom(BLabelBas);

        // Création de la scène
        Scene scene = new Scene(canvas, 700, 500);
        primaryStage.setScene(scene);

        // Affichage de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

