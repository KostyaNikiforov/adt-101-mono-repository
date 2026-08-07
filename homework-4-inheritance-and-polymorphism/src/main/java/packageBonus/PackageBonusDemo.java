package packageBonus;

import package2.BaseMessage;
import package2.FileMessage;
import package2.ImageMessage;
import package2.TextMessage;

import java.io.IOException;
import java.util.ArrayList;

public class PackageBonusDemo {
    public static void main(String[] args) throws IOException {
        User anna = new User("Anna", "+380111111111", "Student");
        User bob = new User("Bob", "+380222222222", "Teacher");

        Chat chat = new Chat("SDT Group");
        chat.addUser(anna);
        chat.addUser(bob);
        chat.addMessage(new TextMessage("2026-08-07", "Anna", "Hello group"));
        chat.addMessage(new ImageMessage("2026-08-07", "Bob", "diagram.png"));
        chat.addMessage(new FileMessage("2026-08-07", "Anna", "homework.pdf"));

        ArrayList<User> users = new ArrayList<>();
        users.add(anna);
        users.add(bob);

        ArrayList<Chat> chats = new ArrayList<>();
        chats.add(chat);

        MessengerStorage storage = new MessengerStorage("messenger-data");
        storage.saveUsers(users);
        storage.saveChats(chats);

        ArrayList<Chat> loadedChats = storage.loadChats();
        for (Chat loadedChat : loadedChats) {
            System.out.println("Chat: " + loadedChat.getChatName());
            for (BaseMessage message : loadedChat.getMessages()) {
                System.out.println(message.render());
            }
            System.out.println("Files: " + loadedChat.getAllFileMessages().size());
            System.out.println("Images: " + loadedChat.getAllImageMessages().size());
        }
    }
}
