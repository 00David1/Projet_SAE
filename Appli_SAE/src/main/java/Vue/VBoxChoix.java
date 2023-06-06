package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class VBoxChoix extends VBox {

    private static SolEffRoot chSoleffroot = new SolEffRoot(); // Instance de la classe SolEffRoot

    private static SolExRoot chSolExRoot = new SolExRoot(); // Instance de la classe SolExRoot

    public VBoxChoix(){

        Label TitrePage = new Label("Veuillez choisir la solution que vous souhaitez tester : ");

        getChildren().add(TitrePage);
        setMargin(TitrePage, new Insets(30, 0, 30, 0));


        //création des boutons pour les choix
        RadioButton btnEfficace = new RadioButton("Efficace");
        RadioButton btnExhaustive = new RadioButton("Exhaustive");
        ToggleGroup toggleGroup = new ToggleGroup();
        btnEfficace.setToggleGroup(toggleGroup);
        btnExhaustive.setToggleGroup(toggleGroup);

        Button btnGo = new Button("Lancer");

        //gestion du placement des boutons
        setMargin(btnEfficace, new Insets(0, 0, 20, 30));
        setMargin(btnExhaustive, new Insets(0, 0, 20, 30));
        setMargin(btnGo, new Insets(0, 0, 0, 45));

        getChildren().addAll(btnEfficace, btnExhaustive, btnGo);

        //création e l'utilisation des boutons
        btnGo.setOnAction(event -> {
            if (btnEfficace.isSelected()) {
                HBoxRoot.setChSoleffroot(chSoleffroot);
            } else if (btnExhaustive.isSelected()) {
                HBoxRoot.setChSoleffroot(chSolExRoot);
            }
        });


    }
}
