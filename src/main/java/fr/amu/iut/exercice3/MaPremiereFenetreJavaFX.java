package fr.amu.iut.exercice3;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MaPremiereFenetreJavaFX extends Application {

    // Label affichant le message de bienvenue
    private Label helloLabel;

    // Champ de saisi du nom de l'utilisateur
    private TextField nameField;

    // Bouton déclenchant la mise à jour du texte
    private Button button;

    // Gestionnaire d'évènements appelé lors du clic sur le bouton
    EventHandler<MouseEvent> buttonClickHandler = actionEvent -> {
        helloLabel.setText( "Bonjour à toi, "+nameField.getText() );
    };



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Création d'un conteneur VBox avec ses éléments centrés
        VBox vbox = new VBox();
        vbox.setAlignment( Pos.CENTER );

        // Création et ajout du label au conteneur
        helloLabel = new Label("Bonjour à tous !");
        vbox.getChildren().add(helloLabel);

        // Ajout d'un champ de saisi de texte de taille 180 pixels
        nameField = new TextField("Veuillez saisir un nom");
        nameField.setMaxWidth(180.0d);
        nameField.setFont( Font.font("Courier", FontWeight.NORMAL, 12) );
        vbox.getChildren().add(nameField);

        // Ajout d'un bouton avec du texte
        button = new Button();
        vbox.getChildren().add( button );

        // Chargement de l'image
        Image image = new Image(MaPremiereFenetreJavaFX.class.getResource("/exercice3/silver_button.png").toString());

        // Création d'un composant avec l'image peinte à l'intérieur
        ImageView iv = new ImageView();
        iv.setImage(image);

        // Intégration de l'image dans le bouton
        button.setGraphic( iv );

        // Changement du texte après un clic sur le bouton
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonClickHandler);

        // Création de la scene
        Scene scene = new Scene( vbox );

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );

        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Hello application");
        primaryStage.show();
    }
}
