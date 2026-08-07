package packageBonus;

import package2.BaseMessage;
import package2.ContactMessage;
import package2.FileMessage;
import package2.ImageMessage;
import package2.LocationMessage;
import package2.TextMessage;
import package2.VoiceMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessengerStorage {
    private final File usersFile;
    private final File chatsFile;

    public MessengerStorage(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        usersFile = new File(folder, "users.txt");
        chatsFile = new File(folder, "chats.txt");
    }

    public void saveUsers(ArrayList<User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for (User user : users) {
                writer.write(user.getNickname() + "|" + user.getPhoneNumber() + "|" + user.getTitle());
                writer.newLine();
            }
        }
    }

    public ArrayList<User> loadUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        if (!usersFile.exists()) {
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                users.add(new User(parts[0], parts[1], parts[2]));
            }
        }
        return users;
    }

    public void saveChats(ArrayList<Chat> chats) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatsFile))) {
            for (Chat chat : chats) {
                writer.write("CHAT|" + chat.getChatName());
                writer.newLine();

                for (User user : chat.getUsers()) {
                    writer.write("USER|" + user.getNickname() + "|" + user.getPhoneNumber() + "|" + user.getTitle());
                    writer.newLine();
                }

                for (BaseMessage message : chat.getMessages()) {
                    writer.write(messageToLine(message));
                    writer.newLine();
                }

                writer.write("ENDCHAT");
                writer.newLine();
            }
        }
    }

    public ArrayList<Chat> loadChats() throws IOException {
        ArrayList<Chat> chats = new ArrayList<>();
        if (!chatsFile.exists()) {
            return chats;
        }

        Chat currentChat = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(chatsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CHAT|")) {
                    currentChat = new Chat(line.substring(5));
                    chats.add(currentChat);
                } else if (line.startsWith("USER|") && currentChat != null) {
                    String[] parts = line.split("\\|");
                    currentChat.addUser(new User(parts[1], parts[2], parts[3]));
                } else if (line.startsWith("MSG|") && currentChat != null) {
                    currentChat.addMessage(lineToMessage(line));
                } else if (line.equals("ENDCHAT")) {
                    currentChat = null;
                }
            }
        }
        return chats;
    }

    private String messageToLine(BaseMessage message) {
        if (message instanceof TextMessage) {
            TextMessage text = (TextMessage) message;
            return "MSG|TEXT|" + text.getDate() + "|" + text.getAuthor() + "|" + text.getText();
        }
        if (message instanceof ImageMessage) {
            ImageMessage image = (ImageMessage) message;
            return "MSG|IMAGE|" + image.getDate() + "|" + image.getAuthor() + "|" + image.getImageUrl();
        }
        if (message instanceof VoiceMessage) {
            VoiceMessage voice = (VoiceMessage) message;
            return "MSG|VOICE|" + voice.getDate() + "|" + voice.getAuthor() + "|" + voice.getDurationSeconds();
        }
        if (message instanceof FileMessage) {
            FileMessage file = (FileMessage) message;
            return "MSG|FILE|" + file.getDate() + "|" + file.getAuthor() + "|" + file.getFileName();
        }
        if (message instanceof LocationMessage) {
            LocationMessage location = (LocationMessage) message;
            return "MSG|LOCATION|" + location.getDate() + "|" + location.getAuthor() + "|" + location.render();
        }
        ContactMessage contact = (ContactMessage) message;
        return "MSG|CONTACT|" + contact.getDate() + "|" + contact.getAuthor() + "|" + contact.render();
    }

    private BaseMessage lineToMessage(String line) {
        String[] parts = line.split("\\|");
        String type = parts[1];
        String date = parts[2];
        String author = parts[3];
        String value = parts[4];

        switch (type) {
            case "IMAGE":
                return new ImageMessage(date, author, value);
            case "VOICE":
                return new VoiceMessage(date, author, Integer.parseInt(value));
            case "FILE":
                return new FileMessage(date, author, value);
            case "LOCATION":
                return new LocationMessage(date, author, 0, 0);
            case "CONTACT":
                return new ContactMessage(date, author, "contact", value);
            default:
                return new TextMessage(date, author, value);
        }
    }
}
