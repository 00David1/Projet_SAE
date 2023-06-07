package vue;

import controleur.Controleur;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    private static VBoxChoix chChoix = new VBoxChoix();

    private static ObservableList<Node> childrens;

    public HBoxRoot() {
        childrens = getChildren();
        childrens.addAll(chChoix);
    }


    public static void setChSoleffroot(GridPane SERoot){

        childrens.clear();
        if (SERoot != null) {
            childrens.addAll(chChoix, SERoot);
            System.out.println("ok");
        } else {
            childrens.add(chChoix);
            System.out.println("null");
        }
    }

    public static VBoxChoix getChChoix(){
        return chChoix;
    }



}
