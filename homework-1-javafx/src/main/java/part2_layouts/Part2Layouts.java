/*
 * ---------------------------------------------------------------
 * Course:        SDT-101 Software Development Technologies
 * Homework:      Homework 1 - JavaFX
 * Part:          Part 2 - JavaFX Layouts
 * Author:        <Your Name>
 * Date:          <Date>
 * Description:   Demonstration of JavaFX layout containers
 *                (Pane, HBox, VBox, GridPane), of alignment,
 *                padding and background, of a hierarchy of
 *                containers and of shapes with their stroke
 *                and fill properties.
 *                Every demonstration is placed on its own tab.
 * ---------------------------------------------------------------
 */
package part2_layouts;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Part2Layouts extends Application {
    @Override
    public void start(Stage stage) {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabs.getTabs().addAll(
                createTab("1. Pane", new PaneDemo()),
                createTab("2. HBox", new HBoxDemo()),
                createTab("3. VBox", new VBoxDemo()),
                createTab("4. GridPane", new GridPaneDemo()),
                createTab("5. Alignment", new AlignmentDemo()),
                createTab("6. Padding", new PaddingDemo()),
                createTab("7. Background", new BackgroundDemo()),
                createTab("8. Hierarchy", new HierarchyDemo()),
                createTab("9. Shapes", new ShapesDemo())
        );

        stage.setTitle("Part 2 - JavaFX Layouts");
        stage.setScene(new Scene(tabs, 720, 500));
        stage.show();
    }

    private Tab createTab(String title, Node content) {
        return new Tab(title, content);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
