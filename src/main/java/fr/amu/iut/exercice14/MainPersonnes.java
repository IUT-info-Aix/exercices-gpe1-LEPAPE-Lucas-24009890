package fr.amu.iut.exercice14;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    private static DoubleProperty ageMoyen = new SimpleDoubleProperty(0);
    private static IntegerProperty nbParisiens = new SimpleIntegerProperty(0);

    public static void main(String[] args) {

        // ObservableList avec écoute des changements sur l'âge et la ville
        lesPersonnes = FXCollections.observableArrayList(
                personne -> new Observable[] { personne.ageProperty(), personne.villeDeNaissanceProperty() }
        );

        // --- Low-level binding : moyenne des âges
        DoubleBinding calculAgeMoyen = new DoubleBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> invalidate());
            }

            @Override
            protected double computeValue() {
                if (lesPersonnes.isEmpty()) return 0;
                double somme = 0;
                for (Personne p : lesPersonnes) {
                    somme += p.getAge();
                }
                return somme / lesPersonnes.size();
            }
        };
        ageMoyen.bind(calculAgeMoyen);

        // --- Low-level binding : nombre de Parisiens
        IntegerBinding calculNbParisiens = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> invalidate());
            }

            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equalsIgnoreCase(p.getVilleDeNaissance())) {
                        count++;
                    }
                }
                return count;
            }
        };
        nbParisiens.bind(calculNbParisiens);

        // Lancement des questions pour tests :
        System.out.println("---- Test question1 ----");
        question1();
        System.out.println("Âge moyen : " + ageMoyen.get()); // Doit s'actualiser automatiquement

        System.out.println("---- Test question2 ----");
        question2();
        System.out.println("Nombre de Parisiens : " + nbParisiens.get());
    }

    public static void question1() {
        lesPersonnes.clear();
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
    }

    public static void question2() {
        lesPersonnes.clear();
        Personne pierre = new Personne("Pierre", 20);
        pierre.setVilleDeNaissance("Paris");

        Personne paul = new Personne("Paul", 40);
        paul.setVilleDeNaissance("Marseille");

        Personne jacques = new Personne("Jacques", 60);
        jacques.setVilleDeNaissance("Paris");

        lesPersonnes.addAll(pierre, paul, jacques);
    }
}
