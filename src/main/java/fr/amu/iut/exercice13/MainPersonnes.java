package fr.amu.iut.exercice13;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {

        // ⚠️ Pour écouter les changements de la propriété age :
        lesPersonnes = FXCollections.observableArrayList(
                personne -> new Observable[] { personne.ageProperty() }
        );

        // Premier listener : un changement à la fois (ajout, suppression, modif)
        unChangementListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println(p.getNom() + " a été ajouté.");
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println(p.getNom() + " a été supprimé.");
                    }
                }
                if (change.wasUpdated()) {
                    System.out.println("Mise à jour détectée.");
                }
            }
        };

        // Deuxième listener : plusieurs changements groupés
        ListChangeListener<Personne> plusieursChangementsListener = change -> {
            System.out.println("Début des changements groupés :");
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println(p.getNom() + " ajouté.");
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println(p.getNom() + " supprimé.");
                    }
                }
                if (change.wasUpdated()) {
                    System.out.println("Mise à jour d’un élément.");
                }
            }
            System.out.println("Fin des changements groupés.");
        };

        // Pour tester : choisis la question à appeler ici

        lesPersonnes.addListener(unChangementListener); // pour question1, 2, 3
//        lesPersonnes.addListener(plusieursChangementsListener); // pour question5

//        question1();
//        question2();
//        question3();
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5); // déclenchement attendu si age est observable
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge() + 10);
        lesPersonnes.removeAll(paul, pierre);
    }
}


