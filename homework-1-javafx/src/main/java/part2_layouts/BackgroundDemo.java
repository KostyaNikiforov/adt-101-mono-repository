package part2_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class BackgroundDemo extends VBox {
    public BackgroundDemo() {
        setSpacing(15);
        setPadding(new Insets(20));
        setBackground(new Background(
                new BackgroundFill(Color.web("#f2f2f2"), CornerRadii.EMPTY, Insets.EMPTY)));

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTSKYBLUE), new Stop(1, Color.MEDIUMPURPLE));

        RadialGradient radial = new RadialGradient(0, 0, 0.5, 0.5, 0.6, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITE), new Stop(1, Color.ORANGE));

        getChildren().addAll(
                new Label("Backgrounds of containers:"),
                createBox("Simple color", new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)),
                createBox("Color with rounded corners", new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(20), Insets.EMPTY)),
                createBox("Linear gradient", new BackgroundFill(gradient, new CornerRadii(10), Insets.EMPTY)),
                createBox("Radial gradient and inner insets", new BackgroundFill(radial, new CornerRadii(10), new Insets(8)))
        );
    }

    private HBox createBox(String text, BackgroundFill fill) {
        HBox box = new HBox(new Label(text));
        box.setAlignment(Pos.CENTER);
        box.setPrefHeight(60);
        box.setBackground(new Background(fill));
        return box;
    }
}
