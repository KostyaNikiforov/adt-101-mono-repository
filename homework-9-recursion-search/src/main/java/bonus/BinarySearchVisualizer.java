package bonus;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchVisualizer extends Application {
    private HBox barsBox;
    private Label statusLabel;
    private Label stepLabel;
    private TextField sizeField;
    private TextField valueField;
    private Slider speedSlider;
    private Button startButton;
    private Button nextButton;
    private Button resetButton;

    private int[] array;
    private List<StackPane> bars = new ArrayList<>();
    private List<int[]> steps = new ArrayList<>();
    private int stepIndex = 0;
    private Timeline timeline;
    private int foundIndex = -1;

    @Override
    public void start(Stage stage) {
        statusLabel = new Label("Set array size and search value, then press Start");
        statusLabel.setFont(new Font(14));
        stepLabel = new Label("Step: 0");

        sizeField = new TextField("20");
        sizeField.setPrefWidth(70);
        valueField = new TextField("14");
        valueField.setPrefWidth(70);

        speedSlider = new Slider(200, 2000, 800);
        speedSlider.setShowTickLabels(true);
        speedSlider.setMajorTickUnit(600);
        speedSlider.setPrefWidth(180);

        startButton = new Button("Start / Auto");
        nextButton = new Button("Next step");
        resetButton = new Button("Reset");
        nextButton.setDisable(true);

        startButton.setOnAction(e -> startSearch(true));
        nextButton.setOnAction(e -> {
            if (steps.isEmpty()) {
                startSearch(false);
            } else {
                showNextStep();
            }
        });
        resetButton.setOnAction(e -> reset());

        HBox controls = new HBox(10,
                new Label("Array size:"), sizeField,
                new Label("Search value:"), valueField,
                new Label("Delay (ms):"), speedSlider,
                startButton, nextButton, resetButton);
        controls.setAlignment(Pos.CENTER_LEFT);

        barsBox = new HBox(4);
        barsBox.setAlignment(Pos.BOTTOM_CENTER);
        barsBox.setPadding(new Insets(20));
        barsBox.setPrefHeight(280);

        VBox root = new VBox(15, controls, statusLabel, stepLabel, barsBox);
        root.setPadding(new Insets(15));

        buildArray(20);
        drawBars(-1, -1, -1);

        stage.setTitle("Binary Search Visualization");
        stage.setScene(new Scene(root, 900, 420));
        stage.show();
    }

    private void startSearch(boolean auto) {
        stopTimeline();

        int size;
        int value;
        try {
            size = Integer.parseInt(sizeField.getText().trim());
            value = Integer.parseInt(valueField.getText().trim());
        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter valid integers for size and value");
            return;
        }

        if (size < 1 || size > 50) {
            statusLabel.setText("Array size should be between 1 and 50");
            return;
        }

        buildArray(size);
        prepareSteps(value);
        stepIndex = 0;
        nextButton.setDisable(false);
        drawBars(-1, -1, -1);
        statusLabel.setText("Searching for " + value + " in sorted array [0 .. " + (size - 1) + "]");
        stepLabel.setText("Step: 0 / " + steps.size());

        if (auto) {
            timeline = new Timeline(new KeyFrame(Duration.millis(speedSlider.getValue()), e -> showNextStep()));
            timeline.setCycleCount(steps.size());
            timeline.play();
        } else {
            showNextStep();
        }
    }

    private void showNextStep() {
        if (stepIndex >= steps.size()) {
            finish();
            return;
        }

        int[] step = steps.get(stepIndex);
        int left = step[0];
        int right = step[1];
        int mid = step[2];
        int state = step[3];

        drawBars(left, right, mid);
        stepIndex++;
        stepLabel.setText("Step: " + stepIndex + " / " + steps.size());

        if (state == 1) {
            statusLabel.setText("Found " + array[mid] + " at index " + mid);
            colorBar(mid, Color.LIGHTGREEN);
            nextButton.setDisable(true);
            stopTimeline();
        } else if (state == -1) {
            statusLabel.setText("Value not found in the array");
            nextButton.setDisable(true);
            stopTimeline();
        } else if (array[mid] < Integer.parseInt(valueField.getText().trim())) {
            statusLabel.setText("mid=" + mid + " value=" + array[mid] + " < target, search right half");
        } else {
            statusLabel.setText("mid=" + mid + " value=" + array[mid] + " > target, search left half");
        }
    }

    private void finish() {
        nextButton.setDisable(true);
        stopTimeline();
    }

    private void reset() {
        stopTimeline();
        stepIndex = 0;
        steps.clear();
        nextButton.setDisable(true);
        int size = 20;
        try {
            size = Integer.parseInt(sizeField.getText().trim());
            if (size < 1 || size > 50) {
                size = 20;
            }
        } catch (NumberFormatException ignored) {
        }
        buildArray(size);
        drawBars(-1, -1, -1);
        statusLabel.setText("Set array size and search value, then press Start");
        stepLabel.setText("Step: 0");
    }

    private void buildArray(int size) {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
    }

    private void prepareSteps(int value) {
        steps.clear();
        foundIndex = -1;
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == value) {
                steps.add(new int[]{left, right, mid, 1});
                foundIndex = mid;
                return;
            } else if (array[mid] < value) {
                steps.add(new int[]{left, right, mid, 0});
                left = mid + 1;
            } else {
                steps.add(new int[]{left, right, mid, 0});
                right = mid - 1;
            }
        }
        steps.add(new int[]{0, array.length - 1, -1, -1});
    }

    private void drawBars(int left, int right, int mid) {
        barsBox.getChildren().clear();
        bars.clear();

        for (int i = 0; i < array.length; i++) {
            Rectangle rect = new Rectangle(Math.max(18, 700.0 / array.length - 4), 40 + array[i] * 4.0);
            rect.setArcWidth(6);
            rect.setArcHeight(6);

            if (mid >= 0 && i == mid) {
                rect.setFill(Color.ORANGE);
            } else if (left >= 0 && i >= left && i <= right) {
                rect.setFill(Color.CORNFLOWERBLUE);
            } else if (left >= 0) {
                rect.setFill(Color.LIGHTGRAY);
            } else {
                rect.setFill(Color.STEELBLUE);
            }

            Label valueLabel = new Label(String.valueOf(array[i]));
            valueLabel.setTextFill(Color.WHITE);
            valueLabel.setFont(new Font(11));

            StackPane bar = new StackPane(rect, valueLabel);
            bars.add(bar);
            barsBox.getChildren().add(bar);
        }
    }

    private void colorBar(int index, Color color) {
        if (index < 0 || index >= bars.size()) {
            return;
        }
        Rectangle rect = (Rectangle) bars.get(index).getChildren().get(0);
        rect.setFill(color);
    }

    private void stopTimeline() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
