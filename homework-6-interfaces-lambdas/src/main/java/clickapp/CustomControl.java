package clickapp;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomControl extends StackPane {
    private final String controlName;
    private final Rectangle background;
    private ControlClickHandler handler;

    public CustomControl(String controlName) {
        this.controlName = controlName;

        background = new Rectangle(90, 90);
        background.setFill(Color.LIGHTGRAY);
        background.setStroke(Color.GRAY);

        Label label = new Label(controlName);
        setAlignment(Pos.CENTER);
        getChildren().addAll(background, label);

        setOnMouseClicked(event -> {
            if (handler != null) {
                handler.controlClicked(this);
            }
        });
    }

    public String getControlName() {
        return controlName;
    }

    public void setOnControlClick(ControlClickHandler handler) {
        this.handler = handler;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            background.setFill(Color.RED);
        } else {
            background.setFill(Color.LIGHTGRAY);
        }
    }
}
