package packageBonus;

import package2.BaseMessage;
import package2.FileMessage;
import package2.ImageMessage;

import java.util.ArrayList;

public class Chat {
    private String chatName;
    private ArrayList<User> users;
    private ArrayList<BaseMessage> messages;

    public Chat(String chatName) {
        this.chatName = chatName;
        users = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public String getChatName() {
        return chatName;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<BaseMessage> getMessages() {
        return messages;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addMessage(BaseMessage message) {
        messages.add(message);
    }

    public ArrayList<FileMessage> getAllFileMessages() {
        ArrayList<FileMessage> result = new ArrayList<>();
        for (BaseMessage message : messages) {
            if (message instanceof FileMessage) {
                result.add((FileMessage) message);
            }
        }
        return result;
    }

    public ArrayList<ImageMessage> getAllImageMessages() {
        ArrayList<ImageMessage> result = new ArrayList<>();
        for (BaseMessage message : messages) {
            if (message instanceof ImageMessage) {
                result.add((ImageMessage) message);
            }
        }
        return result;
    }
}
