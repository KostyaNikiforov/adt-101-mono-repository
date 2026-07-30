package part2_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VBoxDemo extends VBox {
    public VBoxDemo() {
        setSpacing(12);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        Label title = new Label("VBox column:");

        Line separator = new Line(0, 0, 180, 0);
        separator.setStroke(Color.DARKGRAY);
        separator.setStrokeWidth(2);

        Button up = new Button("Top button");
        Button middle = new Button("Middle button");
        Button down = new Button("Bottom button");
        up.setPrefWidth(140);
        middle.setPrefWidth(140);
        down.setPrefWidth(140);

        getChildren().addAll(title, separator, up, middle, down);
    }
}
