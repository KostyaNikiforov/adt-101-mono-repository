package package2;

public class ContactMessage extends BaseMessage {
    private String contactName;
    private String contactPhone;

    public ContactMessage(String date, String author, String contactName, String contactPhone) {
        super(date, author);
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    @Override
    public String render() {
        return "[" + date + "] " + author + " shared contact: " + contactName + " (" + contactPhone + ")";
    }
}
