package tripplanner.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tripplanner.model.Checkpoint;
import tripplanner.model.Trip;
import tripplanner.service.TripPlannerService;

import java.util.ArrayList;

public class CheckpointsView extends VBox {
    private final TripPlannerService service;
    private final Runnable onChange;
    private final ComboBox<Trip> tripBox = new ComboBox<>();
    private final ListView<Checkpoint> checkpointList = new ListView<>();
    private final TextField nameField = new TextField();
    private final TextField locationField = new TextField();
    private final TextField notesField = new TextField();
    private final Label structureLabel = new Label();

    public CheckpointsView(TripPlannerService service, Runnable onChange) {
        this.service = service;
        this.onChange = onChange;

        setSpacing(10);
        setPadding(new Insets(10));

        checkpointList.setPrefHeight(220);
        tripBox.setPrefWidth(420);
        tripBox.setOnAction(e -> refreshCheckpoints());
        checkpointList.getSelectionModel().selectedItemProperty().addListener((obs, o, c) -> fillForm(c));

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Location:"), 0, 1);
        form.add(locationField, 1, 1);
        form.add(new Label("Notes:"), 0, 2);
        form.add(notesField, 1, 2);

        Button addButton = new Button("Add checkpoint");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button refreshTripsButton = new Button("Refresh trips");

        addButton.setOnAction(e -> addCheckpoint());
        updateButton.setOnAction(e -> updateCheckpoint());
        deleteButton.setOnAction(e -> deleteCheckpoint());
        refreshTripsButton.setOnAction(e -> refreshTrips());

        HBox buttons = new HBox(8, addButton, updateButton, deleteButton, refreshTripsButton);

        getChildren().addAll(
                new Label("Route checkpoints (Trip 1 — Checkpoints many). Stored in custom LinkedList."),
                new HBox(8, new Label("Trip:"), tripBox),
                checkpointList,
                structureLabel,
                form,
                buttons
        );
        refreshTrips();
    }

    public void refreshTrips() {
        Trip selected = tripBox.getValue();
        tripBox.setItems(FXCollections.observableArrayList(service.getTrips()));
        if (selected != null) {
            for (Trip trip : service.getTrips()) {
                if (trip.getId().equals(selected.getId())) {
                    tripBox.setValue(trip);
                    break;
                }
            }
        } else if (!service.getTrips().isEmpty()) {
            tripBox.setValue(service.getTrips().get(0));
        }
        refreshCheckpoints();
    }

    private void refreshCheckpoints() {
        Trip trip = tripBox.getValue();
        if (trip == null) {
            checkpointList.setItems(FXCollections.observableArrayList());
            structureLabel.setText("Custom LinkedList size: 0");
            return;
        }
        ArrayList<Checkpoint> items = new ArrayList<>();
        for (Checkpoint checkpoint : trip.getCheckpoints()) {
            items.add(checkpoint);
        }
        checkpointList.setItems(FXCollections.observableArrayList(items));
        structureLabel.setText("Custom LinkedList size: " + trip.getCheckpoints().getSize()
                + " | " + trip.getCheckpoints());
    }

    private void fillForm(Checkpoint checkpoint) {
        if (checkpoint == null) {
            return;
        }
        nameField.setText(checkpoint.getName());
        locationField.setText(checkpoint.getLocation());
        notesField.setText(checkpoint.getNotes());
    }

    private void addCheckpoint() {
        Trip trip = tripBox.getValue();
        if (trip == null) {
            showError("Select a trip first.");
            return;
        }
        service.addCheckpoint(trip, nameField.getText().trim(), locationField.getText().trim(), notesField.getText().trim());
        onChange.run();
        refreshCheckpoints();
        nameField.clear();
        locationField.clear();
        notesField.clear();
    }

    private void updateCheckpoint() {
        Checkpoint selected = checkpointList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a checkpoint.");
            return;
        }
        service.updateCheckpoint(selected, nameField.getText().trim(), locationField.getText().trim(), notesField.getText().trim());
        onChange.run();
        refreshCheckpoints();
    }

    private void deleteCheckpoint() {
        Trip trip = tripBox.getValue();
        Checkpoint selected = checkpointList.getSelectionModel().getSelectedItem();
        if (trip == null || selected == null) {
            showError("Select a trip and a checkpoint.");
            return;
        }
        service.deleteCheckpoint(trip, selected);
        onChange.run();
        refreshCheckpoints();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
