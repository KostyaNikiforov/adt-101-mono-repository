package clickapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickApp extends Application implements ControlClickHandler {
    private Label statusLabel;
    private CustomControl selectedControl;

    @Override
    public void start(Stage stage) {
        statusLabel = new Label("No control selected");

        HBox controlsBox = new HBox(10);
        controlsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 7; i++) {
            CustomControl control = new CustomControl("Control " + i);
            control.setOnControlClick(this);
            controlsBox.getChildren().add(control);
        }

        VBox root = new VBox(20, statusLabel, controlsBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_LEFT);

        stage.setTitle("ClickApp");
        stage.setScene(new Scene(root, 750, 250));
        stage.show();
    }

    @Override
    public void controlClicked(CustomControl control) {
        if (selectedControl != null) {
            selectedControl.setSelected(false);
        }
        selectedControl = control;
        selectedControl.setSelected(true);
        statusLabel.setText("Selected control: " + control.getControlName());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
