package packageBonus;

public class User {
    private String nickname;
    private String phoneNumber;
    private String title;

    public User(String nickname, String phoneNumber, String title) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return nickname + " (" + title + ")";
    }
}
