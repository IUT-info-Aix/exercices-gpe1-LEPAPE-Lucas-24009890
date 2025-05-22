package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création des composants pour Celsius
        Label labelCelsius = new Label("Celsius");
        Slider sliderCelsius = new Slider(0, 100, 20);
        sliderCelsius.setShowTickMarks(true);
        sliderCelsius.setShowTickLabels(true);
        sliderCelsius.setPrefHeight(300);
        sliderCelsius.setOrientation(Orientation.VERTICAL);
        TextField textCelsius = new TextField("20");

        // Création des composants pour Fahrenheit
        Label labelFahrenheit = new Label("Fahrenheit");
        Slider sliderFahrenheit = new Slider(32, 212, 68);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setOrientation(Orientation.VERTICAL);
        sliderFahrenheit.setPrefHeight(300);
        TextField textFahrenheit = new TextField("68");

        // Mise en page
        VBox panneauCelsius = new VBox(10, labelCelsius, sliderCelsius, textCelsius);
        VBox panneauFahrenheit = new VBox(10, labelFahrenheit, sliderFahrenheit, textFahrenheit);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        // Binding bidirectionnel entre les sliders
        DoubleProperty celsiusProperty = sliderCelsius.valueProperty();
        DoubleProperty fahrenheitProperty = sliderFahrenheit.valueProperty();

        celsiusProperty.addListener((observable, oldValue, newValue) -> {
            if (Math.abs((newValue.doubleValue() * 9.0 / 5.0 + 32.0) - fahrenheitProperty.get()) > 0.1) {
                fahrenheitProperty.set(newValue.doubleValue() * 9.0 / 5.0 + 32.0);
            }
        });

        fahrenheitProperty.addListener((observable, oldValue, newValue) -> {
            if (Math.abs(((newValue.doubleValue() - 32.0) * 5.0 / 9.0) - celsiusProperty.get()) > 0.1) {
                celsiusProperty.set((newValue.doubleValue() - 32.0) * 5.0 / 9.0);
            }
        });

        // Binding bidirectionnel entre les sliders et les champs texte
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(textCelsius.textProperty(), celsiusProperty, converter);
        Bindings.bindBidirectional(textFahrenheit.textProperty(), fahrenheitProperty, converter);

        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}