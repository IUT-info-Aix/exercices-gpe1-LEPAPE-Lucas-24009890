module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    exports com.example.partie1;
    exports com.example.partie0;
    exports fr.amu.iut.exercice1;
    exports fr.amu.iut.exercice2;
    opens com.example.partie3 to javafx.fxml;
    exports com.example.partie3;
    exports fr.amu.iut.exercice3 to javafx.graphics;
    opens fr.amu.iut.exercice7 to javafx.fxml;
    exports fr.amu.iut.exercice7;
    opens fr.amu.iut.exercice8 to javafx.fxml;
    exports fr.amu.iut.exercice8;
    opens fr.amu.iut.exercice9 to javafx.fxml;
    exports fr.amu.iut.exercice9;
    opens com.example.partie4 to javafx.fxml;
    exports com.example.partie4;
    opens fr.amu.iut.exercice11 to javafx.fxml;
    exports fr.amu.iut.exercice11;
}
