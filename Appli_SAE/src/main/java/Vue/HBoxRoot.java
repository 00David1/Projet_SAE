package vue;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    private static VBoxChoix chChoix = new VBoxChoix();
    private static GridPaneVide GridPaneVide = new GridPaneVide(chChoix);


    private static ObservableList<Node> childrens;

    public HBoxRoot() {
        childrens = getChildren();
        childrens.addAll(chChoix, GridPaneVide);
    }


    public static void setChSoleffroot(GridPane SERoot){
        childrens.clear();

        if (SERoot != null) {
            childrens.addAll(chChoix, SERoot);
        } else {
            childrens.add(chChoix);
        }
    }

    public static VBoxChoix getChChoix(){
        return chChoix;
    }



}
