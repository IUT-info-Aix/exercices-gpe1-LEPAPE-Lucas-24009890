package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {
    private GridPane root;
    @Override
    public void start(Stage primaryStage) {

        root = new GridPane();
        Random rand = new Random();

        for (int column = 0; column < 3 ; column++)
            for (int row = 0; row < 3; row++) {
                Label l = new Label();
                l.setBorder(new Border(new BorderStroke(Color.LIGHTGREY,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,new BorderWidths(1))));


                switch (rand.nextInt(3)) {
                    case 1 -> l.setGraphic(new ImageView("exercice2/Rond.png"));
                    case 2 -> l.setGraphic(new ImageView("exercice2/Croix.png"));
                    default -> l.setGraphic(new ImageView("exercice2/Vide.png"));
                }
                root.add(l, column, row);
            }

        Scene scene = new Scene(root);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED); -- ne marche pas sur Ubuntu 22.04 ...
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



