package vue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Choix extends VBox {

    private static SolEffRoot chSoleffroot = new SolEffRoot();

    private static SolExRoot chSolExRoot = new SolExRoot();

    public Choix(){
        Button btnEfficace = new Button("Efficace");
        Button btnExhaustive = new Button("Exhaustive");


        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        getChildren().addAll(btnEfficace, btnExhaustive);


        btnEfficace.setOnAction(event -> {
            // Créer une instance de la classe cible
            HBoxRoot.setChSoleffroot(chSoleffroot);
        });


        btnExhaustive.setOnAction(event -> {
            // Créer une instance de la classe cible
            HBoxRoot.setChSoleffroot(chSolExRoot);
        });
    }
}
