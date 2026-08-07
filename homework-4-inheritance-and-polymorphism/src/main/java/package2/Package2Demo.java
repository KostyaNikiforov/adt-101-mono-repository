package package2;

import java.util.ArrayList;

public class Package2Demo {
    public static void main(String[] args) {
        ArrayList<BaseMessage> messages = new ArrayList<>();

        TextMessage text = new TextMessage("2026-08-07", "Anna", "Hello!");
        ImageMessage image = new ImageMessage("2026-08-07", "Bob", "photo.png");
        VoiceMessage voice = new VoiceMessage("2026-08-07", "Kate", 15);
        FileMessage file = new FileMessage("2026-08-07", "Mike", "report.pdf");
        LocationMessage location = new LocationMessage("2026-08-07", "Olga", 50.45, 30.52);
        ContactMessage contact = new ContactMessage("2026-08-07", "Ivan", "Petro", "+380111111111");

        messages.add(text);
        messages.add(image);
        messages.add(voice);
        messages.add(file);
        messages.add(location);
        messages.add(contact);

        for (BaseMessage message : messages) {
            System.out.println(message.render());
        }

        System.out.println(text.renderWithPrefix("MSG"));
    }
}
