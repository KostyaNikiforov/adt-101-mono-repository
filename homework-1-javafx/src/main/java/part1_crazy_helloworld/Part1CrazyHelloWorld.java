package part1_crazy_helloworld;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Part1CrazyHelloWorld extends Application {
    private final TextTransformer transformer = new TextTransformer();
    private final TeleportLog log = new TeleportLog();

    private final TextField startField = new TextField();
    private final Label stationLabel = new Label("(nothing is travelling yet)");
    private final ComboBox<String> parcelBox = new ComboBox<>();
    private final CheckBox sendFurtherBox = new CheckBox("Nothing to send further");

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(12);
        root.setPadding(new Insets(15));

        root.getChildren().addAll(
                createTitle(),
                createStartRow(),
                createModeRow(),
                createLabelRow(),
                createComboRow(),
                new Label("4. Arrivals log:"),
                log.getArea(),
                createReturnRow()
        );

        stage.setTitle("Part 1 - Crazy Value Teleporter");
        stage.setScene(new Scene(root, 520, 470));
        stage.show();
    }

    private Label createTitle() {
        Label title = new Label("Send your text on a round trip!");
        title.setFont(new Font(18));
        return title;
    }

    private HBox createStartRow() {
        startField.setPromptText("Type a word and press Enter or Beam");
        startField.setPrefWidth(280);

        Button beamButton = new Button("Beam it!");
        beamButton.setOnAction(event -> beamToLabel());

        // Enter inside the text field works as an express delivery to the log
        startField.setOnAction(event -> {
            String value = transformer.transform(startField.getText());
            if (!value.isEmpty()) {
                log.write("Express train from TextField", value);
                startField.clear();
            }
        });

        HBox row = new HBox(10, new Label("1."), startField, beamButton);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private HBox createModeRow() {
        ToggleGroup group = new ToggleGroup();
        HBox row = new HBox(10, new Label("Trip effect:"));
        row.setAlignment(Pos.CENTER_LEFT);

        String[] modes = {TextTransformer.AS_IS, TextTransformer.UPPER,
                TextTransformer.REVERSED, TextTransformer.SPACED};

        for (String mode : modes) {
            RadioButton button = new RadioButton(mode);
            button.setToggleGroup(group);
            button.setSelected(mode.equals(TextTransformer.AS_IS));
            button.setOnAction(event -> transformer.setMode(mode));
            row.getChildren().add(button);
        }
        return row;
    }

    private HBox createLabelRow() {
        stationLabel.setFont(new Font(15));

        Button packButton = new Button("Pack into ComboBox");
        packButton.setOnAction(event -> packIntoCombo());

        HBox row = new HBox(10, new Label("2."), stationLabel, packButton);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private HBox createComboRow() {
        parcelBox.setPrefWidth(200);
        parcelBox.setPromptText("empty storage");
        parcelBox.setOnAction(event -> {
            String selected = parcelBox.getValue();
            if (selected != null) {
                sendFurtherBox.setSelected(false);
                sendFurtherBox.setText("Send \"" + selected + "\" to the log");
            }
        });

        sendFurtherBox.setOnAction(event -> {
            if (sendFurtherBox.isSelected() && parcelBox.getValue() != null) {
                log.write("ComboBox", transformer.transform(parcelBox.getValue()));
            }
        });

        HBox row = new HBox(10, new Label("3."), parcelBox, sendFurtherBox);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private HBox createReturnRow() {
        Button returnButton = new Button("Copy to start");
        returnButton.setOnAction(event -> {
            if (!log.getLastValue().isEmpty()) {
                startField.setText(transformer.transform(log.getLastValue()));
                stationLabel.setText("the value came back home");
            }
        });

        Button clearButton = new Button("Clear everything");
        clearButton.setOnAction(event -> clearAll());

        HBox row = new HBox(10, new Label("5."), returnButton, clearButton);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private void beamToLabel() {
        String value = startField.getText();
        if (value.isEmpty()) {
            stationLabel.setText("the teleporter is empty, type something first");
            return;
        }
        stationLabel.setText(transformer.transform(value));
        startField.clear();
    }

    private void packIntoCombo() {
        String value = stationLabel.getText();
        if (value.startsWith("(") || value.startsWith("the ")) {
            return;
        }
        String packed = transformer.transform(value);
        parcelBox.getItems().add(packed);
        parcelBox.setValue(packed);
        stationLabel.setText("(the parcel is in the ComboBox)");
    }

    private void clearAll() {
        startField.clear();
        stationLabel.setText("(nothing is travelling yet)");
        parcelBox.getItems().clear();
        parcelBox.setValue(null);
        sendFurtherBox.setSelected(false);
        sendFurtherBox.setText("Nothing to send further");
        log.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
