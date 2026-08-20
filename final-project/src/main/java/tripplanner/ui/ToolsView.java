package tripplanner.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tripplanner.model.Trip;
import tripplanner.service.TripPlannerService;

public class ToolsView extends VBox {
    private final TripPlannerService service;
    private final Runnable onChange;
    private final ListView<Trip> sortedList = new ListView<>();
    private final TextField searchField = new TextField();
    private final TextArea resultArea = new TextArea();

    public ToolsView(TripPlannerService service, Runnable onChange) {
        this.service = service;
        this.onChange = onChange;

        setSpacing(10);
        setPadding(new Insets(10));

        sortedList.setPrefHeight(220);
        resultArea.setPrefRowCount(6);
        resultArea.setEditable(false);
        searchField.setPromptText("Trip title exact match");

        Button sortButton = new Button("Sort trips by distance (Merge Sort)");
        Button searchButton = new Button("Binary search by title");
        Button showStackButton = new Button("Show undo Stack");

        sortButton.setOnAction(e -> {
            sortedList.setItems(FXCollections.observableArrayList(service.sortTripsByDistance()));
            resultArea.setText("Trips sorted by distance using custom Merge Sort.");
        });

        searchButton.setOnAction(e -> {
            Trip found = service.searchTripByTitle(searchField.getText().trim());
            if (found == null) {
                resultArea.setText("Not found: \"" + searchField.getText().trim() + "\"");
            } else {
                resultArea.setText("Found by binary search:\n"
                        + found.getTitle() + "\n"
                        + found.getStartDate() + " - " + found.getEndDate() + "\n"
                        + found.getDistanceKm() + " km\n"
                        + found.getDescription());
            }
        });

        showStackButton.setOnAction(e -> {
            resultArea.setText("Custom Stack of deleted trips (for Undo):\n"
                    + "size = " + service.getDeletedTrips().size() + "\n"
                    + service.getDeletedTrips());
            onChange.run();
        });

        getChildren().addAll(
                new Label("Algorithms & custom structures demo"),
                sortButton,
                sortedList,
                new HBox(8, searchField, searchButton),
                showStackButton,
                resultArea
        );
    }
}
