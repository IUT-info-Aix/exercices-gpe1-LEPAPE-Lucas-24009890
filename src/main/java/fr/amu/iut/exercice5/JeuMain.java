package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private boolean jeu = true;

    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);

        // Créer des obstacles
        Obstacle mur1 = new Obstacle(150, 100, 50, 200);
        Obstacle mur2 = new Obstacle(300, 50, 20, 150);
        obstacles.add(mur1);
        obstacles.add(mur2);

        jeu.getChildren().addAll(mur1, mur2, pacman, fantome);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */

    private void afficherGameOver() {
        // Crée un nouveau panneau avec le message Game Over
        Pane gameOverPane = new Pane();
        gameOverPane.setPrefSize(640, 480);

        javafx.scene.text.Text text = new javafx.scene.text.Text("  Toi = Looser");
        text.setStyle("-fx-font-size: 50px; -fx-fill: red;");
        text.setLayoutX(150);
        text.setLayoutY(240);

        gameOverPane.getChildren().add(text);
        root.setCenter(gameOverPane); // Remplace le panneau central actuel
    }

    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (!jeu)
                return;

            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getWidth());
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;

            }
            if (j1.estEnCollision(j2)) {
                System.out.println("Collision....");
                jeu = false;
                afficherGameOver();
            }
        });
    }
}
