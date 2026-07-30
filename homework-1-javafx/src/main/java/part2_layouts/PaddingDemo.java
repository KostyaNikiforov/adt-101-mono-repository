package part2_layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaddingDemo extends VBox {
    public PaddingDemo() {
        setSpacing(15);
        setPadding(new Insets(20));

        StackPane inner = new StackPane(new Label("I am the child node"));
        inner.setBackground(new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        HBox outer = new HBox(inner);
        outer.setPadding(new Insets(10));
        outer.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), Insets.EMPTY)));

        Slider slider = new Slider(0, 60, 10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(20);

        Label info = new Label("Padding of the green box: 10");

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int padding = newValue.intValue();
            outer.setPadding(new Insets(padding));
            info.setText("Padding of the green box: " + padding);
        });

        // different padding on each side is also possible
        HBox unevenBox = new HBox(new Label("Padding 40 on the left, 5 on the other sides"));
        unevenBox.setPadding(new Insets(5, 5, 5, 40));
        unevenBox.setBackground(new Background(
                new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(5), Insets.EMPTY)));

        getChildren().addAll(info, slider, outer, unevenBox);
    }
}
