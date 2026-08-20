package tripplanner.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tripplanner.service.TripPlannerService;

import java.io.IOException;

public class TripPlannerApp extends Application {
    private TripPlannerService service;

    @Override
    public void start(Stage stage) {
        service = new TripPlannerService("trip-data.mtp");
        try {
            service.load();
            service.seedSampleDataIfEmpty();
            service.save();
        } catch (IOException e) {
            showError("Could not load data: " + e.getMessage());
        }

        TabPane tabs = new TabPane();
        tabs.getTabs().addAll(
                createTab("Riders", new RidersView(service, this::persist)),
                createTab("Trips", new TripsView(service, this::persist)),
                createTab("Route Checkpoints", new CheckpointsView(service, this::persist)),
                createTab("Sort & Search", new ToolsView(service, this::persist))
        );

        BorderPane root = new BorderPane(tabs);
        root.setPadding(new Insets(10));

        stage.setTitle("Motorcycle Trip Planner");
        stage.setScene(new Scene(root, 980, 620));
        stage.show();

        stage.setOnCloseRequest(event -> persist());
    }

    private Tab createTab(String title, javafx.scene.Node content) {
        Tab tab = new Tab(title, content);
        tab.setClosable(false);
        return tab;
    }

    public void persist() {
        try {
            service.save();
        } catch (IOException e) {
            showError("Could not save data: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
