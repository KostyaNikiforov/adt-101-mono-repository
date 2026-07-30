package part2_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HBoxDemo extends HBox {
    public HBoxDemo() {
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("HBox row:");
        Button first = new Button("First");
        Button second = new Button("Second");
        Button third = new Button("Third");

        Rectangle marker = new Rectangle(25, 25, Color.MEDIUMPURPLE);
        marker.setArcWidth(10);
        marker.setArcHeight(10);

        getChildren().addAll(title, first, second, third, marker);
    }
}
