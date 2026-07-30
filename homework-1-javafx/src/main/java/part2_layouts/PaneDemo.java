package part2_layouts;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PaneDemo extends Pane {
    public PaneDemo() {
        Label hint = new Label("Pane places every node by X and Y coordinates");
        hint.relocate(20, 15);

        Rectangle rectangle = new Rectangle(60, 40, Color.LIGHTSTEELBLUE);
        rectangle.relocate(40, 60);
        rectangle.setStroke(Color.STEELBLUE);

        Circle circle = new Circle(30, Color.LIGHTSALMON);
        circle.relocate(160, 70);
        circle.setStroke(Color.CORAL);

        Label free = new Label("even labels can overlap here");
        free.relocate(70, 90);

        getChildren().addAll(hint, rectangle, circle, free);
    }
}
