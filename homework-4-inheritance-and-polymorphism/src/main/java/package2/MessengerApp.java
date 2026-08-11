package package2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessengerApp extends Application {
    private final List<BaseMessage> messages = new ArrayList<>();
    private final ListView<String> messageList = new ListView<>();

    @Override
    public void start(Stage stage) {
        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Text", "Image", "Voice", "File", "Location", "Contact");
        typeBox.setValue("Text");

        TextField contentField = new TextField();
        contentField.setPromptText("Message content");

        Button addButton = new Button("Add Message");
        addButton.setOnAction(event -> addMessage(authorField.getText(), typeBox.getValue(), contentField.getText()));

        Label title = new Label("Messenger Demo");
        HBox inputRow = new HBox(10, authorField, typeBox, contentField, addButton);
        VBox root = new VBox(10, title, inputRow, messageList);
        root.setPadding(new Insets(15));

        stage.setTitle("Messenger");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
    }

    private void addMessage(String author, String type, String content) {
        if (author == null || author.isEmpty() || content == null || content.isEmpty()) {
            return;
        }

        String date = LocalDate.now().toString();
        BaseMessage message;

        switch (type) {
            case "Image":
                message = new ImageMessage(date, author, content);
                break;
            case "Voice":
                message = new VoiceMessage(date, author, Integer.parseInt(content));
                break;
            case "File":
                message = new FileMessage(date, author, content);
                break;
            case "Location":
                String[] parts = content.split(",");
                message = new LocationMessage(date, author, Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
                break;
            case "Contact":
                String[] contactParts = content.split(",");
                message = new ContactMessage(date, author, contactParts[0], contactParts[1]);
                break;
            default:
                message = new TextMessage(date, author, content);
                break;
        }

        messages.add(message);
        messageList.getItems().add(message.render());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
