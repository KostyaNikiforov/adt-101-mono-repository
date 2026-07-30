package part2_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HierarchyDemo extends BorderPane {
    public HierarchyDemo() {
        setTop(createHeader());
        setLeft(createMenu());
        setCenter(createContent());
        setBottom(createStatusBar());
    }

    private HBox createHeader() {
        Label title = new Label("My Player");
        title.setFont(new Font(20));
        title.setTextFill(Color.WHITE);

        HBox header = new HBox(title);
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setBackground(new Background(
                new BackgroundFill(Color.web("#37474f"), CornerRadii.EMPTY, Insets.EMPTY)));
        return header;
    }

    private VBox createMenu() {
        ListView<String> playlist = new ListView<>();
        playlist.getItems().addAll("Song one", "Song two", "Song three", "Song four");
        playlist.setPrefWidth(160);

        VBox menu = new VBox(10, new Label("Playlist"), playlist);
        menu.setPadding(new Insets(12));
        menu.setBackground(new Background(
                new BackgroundFill(Color.web("#eceff1"), CornerRadii.EMPTY, Insets.EMPTY)));
        return menu;
    }

    /** The center part is a VBox that contains a GridPane and an HBox with buttons. */
    private VBox createContent() {
        GridPane info = new GridPane();
        info.setHgap(10);
        info.setVgap(8);
        info.add(new Label("Title:"), 0, 0);
        info.add(new Label("Song one"), 1, 0);
        info.add(new Label("Artist:"), 0, 1);
        info.add(new Label("Unknown band"), 1, 1);
        info.add(new Label("Length:"), 0, 2);
        info.add(new Label("3:45"), 1, 2);

        HBox buttons = new HBox(10, new Button("Prev"), new Button("Play"), new Button("Next"));
        buttons.setAlignment(Pos.CENTER);

        VBox content = new VBox(20, new Label("Now playing"), info, buttons);
        content.setPadding(new Insets(20));
        return content;
    }

    private HBox createStatusBar() {
        HBox status = new HBox(new Label("Ready. BorderPane > (HBox, VBox, GridPane)"));
        status.setPadding(new Insets(8));
        status.setBackground(new Background(
                new BackgroundFill(Color.web("#cfd8dc"), CornerRadii.EMPTY, Insets.EMPTY)));
        return status;
    }
}
