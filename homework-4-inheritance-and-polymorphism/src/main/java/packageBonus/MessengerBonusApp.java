package packageBonus;

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
import package2.BaseMessage;
import package2.FileMessage;
import package2.ImageMessage;
import package2.TextMessage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MessengerBonusApp extends Application {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Chat> chats = new ArrayList<>();
    private final MessengerStorage storage = new MessengerStorage("messenger-data");

    private final ListView<String> chatList = new ListView<>();
    private final ListView<String> messageList = new ListView<>();
    private Chat selectedChat;

    @Override
    public void start(Stage stage) {
        TextField chatNameField = new TextField();
        chatNameField.setPromptText("Chat name");

        TextField userField = new TextField();
        userField.setPromptText("User nickname");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField messageField = new TextField();
        messageField.setPromptText("Message text");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Text", "Image", "File");
        typeBox.setValue("Text");

        Button createChatButton = new Button("Create Chat");
        Button addUserButton = new Button("Add User");
        Button addMessageButton = new Button("Add Message");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");

        createChatButton.setOnAction(event -> createChat(chatNameField.getText()));
        addUserButton.setOnAction(event -> addUser(userField.getText(), phoneField.getText(), titleField.getText()));
        addMessageButton.setOnAction(event -> addMessage(authorField.getText(), typeBox.getValue(), messageField.getText()));
        saveButton.setOnAction(event -> saveData());
        loadButton.setOnAction(event -> loadData());

        chatList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> selectChat(newValue));

        HBox chatRow = new HBox(10, chatNameField, createChatButton);
        HBox userRow = new HBox(10, userField, phoneField, titleField, addUserButton);
        HBox messageRow = new HBox(10, authorField, typeBox, messageField, addMessageButton);
        HBox fileRow = new HBox(10, saveButton, loadButton);

        VBox root = new VBox(10,
                new Label("Messenger Bonus"),
                chatRow,
                userRow,
                messageRow,
                fileRow,
                new Label("Chats"),
                chatList,
                new Label("Messages"),
                messageList
        );
        root.setPadding(new Insets(15));

        stage.setTitle("Messenger Bonus");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    private void createChat(String chatName) {
        if (chatName == null || chatName.isEmpty()) {
            return;
        }
        Chat chat = new Chat(chatName);
        chats.add(chat);
        chatList.getItems().add(chatName);
    }

    private void addUser(String nickname, String phone, String title) {
        if (nickname == null || nickname.isEmpty()) {
            return;
        }
        User user = new User(nickname, phone, title);
        users.add(user);
        if (selectedChat != null) {
            selectedChat.addUser(user);
        }
    }

    private void addMessage(String author, String type, String content) {
        if (selectedChat == null || author == null || author.isEmpty() || content == null || content.isEmpty()) {
            return;
        }

        String date = LocalDate.now().toString();
        BaseMessage message;

        if ("Image".equals(type)) {
            message = new ImageMessage(date, author, content);
        } else if ("File".equals(type)) {
            message = new FileMessage(date, author, content);
        } else {
            message = new TextMessage(date, author, content);
        }

        selectedChat.addMessage(message);
        messageList.getItems().add(message.render());
    }

    private void selectChat(String chatName) {
        selectedChat = null;
        messageList.getItems().clear();
        for (Chat chat : chats) {
            if (chat.getChatName().equals(chatName)) {
                selectedChat = chat;
                for (BaseMessage message : chat.getMessages()) {
                    messageList.getItems().add(message.render());
                }
                break;
            }
        }
    }

    private void saveData() {
        try {
            storage.saveUsers(users);
            storage.saveChats(chats);
        } catch (IOException e) {
            messageList.getItems().add("Save error: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            users.clear();
            chats.clear();
            chatList.getItems().clear();
            messageList.getItems().clear();

            users.addAll(storage.loadUsers());
            chats.addAll(storage.loadChats());

            for (Chat chat : chats) {
                chatList.getItems().add(chat.getChatName());
            }
        } catch (IOException e) {
            messageList.getItems().add("Load error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
