package tripplanner.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tripplanner.model.Rider;
import tripplanner.model.Trip;
import tripplanner.service.TripPlannerService;

public class TripsView extends VBox {
    private final TripPlannerService service;
    private final Runnable onChange;
    private final ListView<Trip> tripList = new ListView<>();
    private final ListView<Rider> allRidersList = new ListView<>();
    private final ListView<Rider> tripRidersList = new ListView<>();

    private final TextField titleField = new TextField();
    private final TextField startField = new TextField();
    private final TextField endField = new TextField();
    private final TextField distanceField = new TextField();
    private final TextArea descriptionArea = new TextArea();

    public TripsView(TripPlannerService service, Runnable onChange) {
        this.service = service;
        this.onChange = onChange;

        setSpacing(10);
        setPadding(new Insets(10));

        tripList.setPrefHeight(180);
        allRidersList.setPrefHeight(120);
        tripRidersList.setPrefHeight(120);
        descriptionArea.setPrefRowCount(3);

        tripList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, trip) -> {
            fillForm(trip);
            refreshTripRiders();
        });

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("Start date (YYYY-MM-DD):"), 0, 1);
        form.add(startField, 1, 1);
        form.add(new Label("End date (YYYY-MM-DD):"), 0, 2);
        form.add(endField, 1, 2);
        form.add(new Label("Distance km:"), 0, 3);
        form.add(distanceField, 1, 3);
        form.add(new Label("Description:"), 0, 4);
        form.add(descriptionArea, 1, 4);

        Button addButton = new Button("Add trip");
        Button updateButton = new Button("Update trip");
        Button deleteButton = new Button("Delete trip");
        Button undoButton = new Button("Undo delete");
        Button assignButton = new Button("Assign rider ->");
        Button unassignButton = new Button("<- Unassign rider");

        addButton.setOnAction(e -> addTrip());
        updateButton.setOnAction(e -> updateTrip());
        deleteButton.setOnAction(e -> deleteTrip());
        undoButton.setOnAction(e -> undoDelete());
        assignButton.setOnAction(e -> assignRider());
        unassignButton.setOnAction(e -> unassignRider());

        HBox tripButtons = new HBox(8, addButton, updateButton, deleteButton, undoButton);
        HBox riderButtons = new HBox(8, assignButton, unassignButton);

        HBox riderBox = new HBox(10,
                new VBox(5, new Label("All riders"), allRidersList),
                new VBox(5, new Label(" "), riderButtons),
                new VBox(5, new Label("Riders on selected trip"), tripRidersList)
        );

        getChildren().addAll(
                new Label("Trips (CRUD) + many-to-many with Riders"),
                tripList,
                form,
                tripButtons,
                new Label("Assign riders to trip (many-to-many)"),
                riderBox
        );
        refresh();
    }

    public void refresh() {
        tripList.setItems(FXCollections.observableArrayList(service.getTrips()));
        allRidersList.setItems(FXCollections.observableArrayList(service.getRiders()));
        refreshTripRiders();
    }

    private void refreshTripRiders() {
        Trip trip = tripList.getSelectionModel().getSelectedItem();
        if (trip == null) {
            tripRidersList.setItems(FXCollections.observableArrayList());
            return;
        }
        tripRidersList.setItems(FXCollections.observableArrayList(service.getRidersForTrip(trip)));
    }

    private void fillForm(Trip trip) {
        if (trip == null) {
            return;
        }
        titleField.setText(trip.getTitle());
        startField.setText(trip.getStartDate());
        endField.setText(trip.getEndDate());
        distanceField.setText(String.valueOf(trip.getDistanceKm()));
        descriptionArea.setText(trip.getDescription());
    }

    private void addTrip() {
        try {
            service.addTrip(
                    titleField.getText().trim(),
                    startField.getText().trim(),
                    endField.getText().trim(),
                    Double.parseDouble(distanceField.getText().trim()),
                    descriptionArea.getText().trim()
            );
            onChange.run();
            refresh();
        } catch (Exception ex) {
            showError("Check trip fields. Distance must be a number.");
        }
    }

    private void updateTrip() {
        Trip selected = tripList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a trip to update.");
            return;
        }
        try {
            service.updateTrip(
                    selected,
                    titleField.getText().trim(),
                    startField.getText().trim(),
                    endField.getText().trim(),
                    Double.parseDouble(distanceField.getText().trim()),
                    descriptionArea.getText().trim()
            );
            onChange.run();
            refresh();
        } catch (Exception ex) {
            showError("Check trip fields. Distance must be a number.");
        }
    }

    private void deleteTrip() {
        Trip selected = tripList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a trip to delete.");
            return;
        }
        service.deleteTrip(selected);
        onChange.run();
        refresh();
    }

    private void undoDelete() {
        if (!service.undoDeleteTrip()) {
            showError("No deleted trip to restore. Custom Stack is empty.");
            return;
        }
        onChange.run();
        refresh();
    }

    private void assignRider() {
        Trip trip = tripList.getSelectionModel().getSelectedItem();
        Rider rider = allRidersList.getSelectionModel().getSelectedItem();
        if (trip == null || rider == null) {
            showError("Select a trip and a rider.");
            return;
        }
        service.assignRider(trip, rider);
        onChange.run();
        refreshTripRiders();
    }

    private void unassignRider() {
        Trip trip = tripList.getSelectionModel().getSelectedItem();
        Rider rider = tripRidersList.getSelectionModel().getSelectedItem();
        if (trip == null || rider == null) {
            showError("Select a trip and an assigned rider.");
            return;
        }
        service.unassignRider(trip, rider);
        onChange.run();
        refreshTripRiders();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
