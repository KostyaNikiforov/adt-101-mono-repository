package part2_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AlignmentDemo extends VBox {
    private static final Pos[] POSITIONS = {
            Pos.TOP_LEFT, Pos.TOP_CENTER, Pos.TOP_RIGHT,
            Pos.CENTER_LEFT, Pos.CENTER, Pos.CENTER_RIGHT,
            Pos.BOTTOM_LEFT, Pos.BOTTOM_CENTER, Pos.BOTTOM_RIGHT
    };

    public AlignmentDemo() {
        setSpacing(10);
        setPadding(new Insets(20));

        getChildren().add(new Label("Nine boxes with different Alignment:"));

        HBox row = new HBox(10);
        for (Pos position : POSITIONS) {
            row.getChildren().add(createBox(position));
            // three boxes in a row
            if (row.getChildren().size() == 3) {
                getChildren().add(row);
                row = new HBox(10);
            }
        }
    }

    private StackPane createBox(Pos position) {
        Button button = new Button(position.toString());
        button.setMnemonicParsing(false); // otherwise "_" in the name is not shown

        StackPane box = new StackPane(button);
        box.setAlignment(position);
        box.setPrefSize(180, 70);
        box.setBackground(new Background(
                new BackgroundFill(Color.web("#eef3fa"), new CornerRadii(8), Insets.EMPTY)));
        return box;
    }
}
