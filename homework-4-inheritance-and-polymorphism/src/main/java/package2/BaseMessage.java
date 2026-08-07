package package2;

public abstract class BaseMessage {
    protected String date;
    protected String author;

    public BaseMessage(String date, String author) {
        this.date = date;
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public abstract String render();

    public String renderWithPrefix(String prefix) {
        return prefix + ": " + render();
    }
}
