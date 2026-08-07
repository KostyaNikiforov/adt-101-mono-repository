package package2;

public class FileMessage extends BaseMessage {
    private String fileName;

    public FileMessage(String date, String author, String fileName) {
        super(date, author);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String render() {
        return "[" + date + "] " + author + " sent file: " + fileName;
    }
}
