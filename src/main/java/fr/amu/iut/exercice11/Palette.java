package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private Button btnVert;
    private Button btnRouge;
    private Button btnBleu;

    // Propriétés pour les compteurs spécifiques à chaque couleur
    private IntegerProperty nbFoisVert;
    private IntegerProperty nbFoisRouge;
    private IntegerProperty nbFoisBleu;

    // Propriétés demandées dans l'exercice
    private IntegerProperty nbFois;        // Compteur actuel (dépend de la couleur sélectionnée)
    private StringProperty message;        // Message à afficher
    private StringProperty couleurPanneau; // Couleur du panneau
    private StringProperty couleurChoisie; // Couleur sélectionnée actuellement

    @Override
    public void start(Stage primaryStage) {
        // Initialisation de l'interface
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Création du panneau central qui changera de couleur
        panneau = new Pane();
        panneau.setPrefSize(400, 200);
        root.setCenter(panneau);

        // Création du texte du haut
        texteDuHaut = new Label();
        texteDuHaut.setAlignment(Pos.CENTER);
        root.setTop(texteDuHaut);

        // Création des boutons
        btnVert = new Button("Vert");
        btnRouge = new Button("Rouge");
        btnBleu = new Button("Bleu");

        // Création du conteneur de boutons
        HBox boutonsBox = new HBox(10, btnVert, btnRouge, btnBleu);
        boutonsBox.setAlignment(Pos.CENTER);

        // Création du texte du bas
        texteDuBas = new Label();
        texteDuBas.setAlignment(Pos.CENTER);

        // Placement des boutons et du texte du bas
        VBox bottomBox = new VBox(10, boutonsBox, texteDuBas);
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);

        // Initialisation des propriétés
        nbFoisVert = new SimpleIntegerProperty(0);
        nbFoisRouge = new SimpleIntegerProperty(0);
        nbFoisBleu = new SimpleIntegerProperty(0);

        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("");
        couleurPanneau = new SimpleStringProperty("#000000");
        couleurChoisie = new SimpleStringProperty("");

        // Création des bindings
        createBindings();

        // Ajout des événements aux boutons
        setupButtonHandlers();

        // Configuration de la scène
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Choix de couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupButtonHandlers() {
        // Gestionnaire pour le bouton Vert
        btnVert.setOnAction(event -> {
            nbFoisVert.set(nbFoisVert.get() + 1);
            nbFois.set(nbFoisVert.get());
            couleurChoisie.set("Vert");
            message.set("Le Vert est une jolie couleur !");
            couleurPanneau.set("#00FF00");
        });

        // Gestionnaire pour le bouton Rouge
        btnRouge.setOnAction(event -> {
            nbFoisRouge.set(nbFoisRouge.get() + 1);
            nbFois.set(nbFoisRouge.get());
            couleurChoisie.set("Rouge");
            message.set("Le Rouge est une jolie couleur !");
            couleurPanneau.set("#FF0000");
        });

        // Gestionnaire pour le bouton Bleu
        btnBleu.setOnAction(event -> {
            nbFoisBleu.set(nbFoisBleu.get() + 1);
            nbFois.set(nbFoisBleu.get());
            couleurChoisie.set("Bleu");
            message.set("Le Bleu est une jolie couleur !");
            couleurPanneau.set("#0000FF");
        });
    }

    private void createBindings() {
        // Création d'une propriété pour savoir si un clic a été fait
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        // Binding pour le texte du haut
        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Cliquez sur un bouton")
                        .otherwise(Bindings.concat(
                                couleurChoisie, " choisi ", nbFois.asString(), " fois"
                        ))
        );

        // Binding pour la couleur du panneau
        panneau.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau, ";")
        );

        // Binding pour le texte du bas
        texteDuBas.textProperty().bind(message);
        texteDuBas.styleProperty().bind(
                Bindings.concat("-fx-text-fill: ", couleurPanneau, ";")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
