package tripplanner.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tripplanner.model.Rider;
import tripplanner.service.TripPlannerService;

public class RidersView extends VBox {
    private final TripPlannerService service;
    private final Runnable onChange;
    private final ListView<Rider> listView = new ListView<>();
    private final TextField nameField = new TextField();
    private final TextField phoneField = new TextField();
    private final TextField bikeField = new TextField();
    private final TextField experienceField = new TextField();

    public RidersView(TripPlannerService service, Runnable onChange) {
        this.service = service;
        this.onChange = onChange;

        setSpacing(10);
        setPadding(new Insets(10));

        listView.setPrefHeight(280);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, rider) -> fillForm(rider));

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Phone:"), 0, 1);
        form.add(phoneField, 1, 1);
        form.add(new Label("Bike model:"), 0, 2);
        form.add(bikeField, 1, 2);
        form.add(new Label("Experience years:"), 0, 3);
        form.add(experienceField, 1, 3);

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");

        addButton.setOnAction(e -> addRider());
        updateButton.setOnAction(e -> updateRider());
        deleteButton.setOnAction(e -> deleteRider());
        clearButton.setOnAction(e -> clearForm());

        HBox buttons = new HBox(8, addButton, updateButton, deleteButton, clearButton);
        getChildren().addAll(new Label("Riders (CRUD)"), listView, form, buttons);
        refresh();
    }

    public void refresh() {
        listView.setItems(FXCollections.observableArrayList(service.getRiders()));
    }

    private void fillForm(Rider rider) {
        if (rider == null) {
            return;
        }
        nameField.setText(rider.getName());
        phoneField.setText(rider.getPhone());
        bikeField.setText(rider.getBikeModel());
        experienceField.setText(String.valueOf(rider.getExperienceYears()));
    }

    private void addRider() {
        try {
            service.addRider(
                    nameField.getText().trim(),
                    phoneField.getText().trim(),
                    bikeField.getText().trim(),
                    Integer.parseInt(experienceField.getText().trim())
            );
            onChange.run();
            refresh();
            clearForm();
        } catch (Exception ex) {
            showError("Check rider fields. Experience must be a number.");
        }
    }

    private void updateRider() {
        Rider selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a rider to update.");
            return;
        }
        try {
            service.updateRider(
                    selected,
                    nameField.getText().trim(),
                    phoneField.getText().trim(),
                    bikeField.getText().trim(),
                    Integer.parseInt(experienceField.getText().trim())
            );
            onChange.run();
            refresh();
        } catch (Exception ex) {
            showError("Check rider fields. Experience must be a number.");
        }
    }

    private void deleteRider() {
        Rider selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a rider to delete.");
            return;
        }
        service.deleteRider(selected);
        onChange.run();
        refresh();
        clearForm();
    }

    private void clearForm() {
        nameField.clear();
        phoneField.clear();
        bikeField.clear();
        experienceField.clear();
        listView.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
