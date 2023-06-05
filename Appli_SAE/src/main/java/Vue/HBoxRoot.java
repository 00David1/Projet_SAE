package vue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    private static Choix chChoix = new Choix();
    private static SolEffRoot chSoleffroot = new SolEffRoot();
    private static ObservableList<Node> childrens;

    public HBoxRoot() {
        chChoix = new Choix();
        childrens = getChildren();
        childrens.addAll(chChoix);
    }

    public static Choix getChChoix(){
        return chChoix;
    }

    public static SolEffRoot getChSoleffroot(){
        return chSoleffroot;
    }

    public static void setChSoleffroot(GridPane SERoot){
        childrens.clear();
        childrens.addAll(chChoix, SERoot);
    }
}

