package fr.amu.iut.exercice15;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void initialize() {
        createBindings();
    }

    private void createBindings() {
        // Le champ pwd n'est éditable que si userId a au moins 6 caractères
        pwd.editableProperty().bind(userId.textProperty().length().greaterThanOrEqualTo(6));

        // Le bouton cancel est désactivé si userId ET pwd sont vides
        cancelBtn.disableProperty().bind(
                userId.textProperty().isEmpty().and(pwd.textProperty().isEmpty())
        );

        // Le bouton ok est désactivé tant que le mot de passe n'a pas au moins 8 caractères,
        // contient au moins une majuscule et un chiffre
        okBtn.disableProperty().bind(new BooleanBinding() {
            {
                super.bind(pwd.textProperty());
            }

            @Override
            protected boolean computeValue() {
                String password = pwd.getText();
                if (password.length() < 8) return true;
                if (!password.matches(".*[A-Z].*")) return true; // pas de majuscule
                if (!password.matches(".*\\d.*")) return true;   // pas de chiffre
                return false; // mot de passe OK
            }
        });
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}