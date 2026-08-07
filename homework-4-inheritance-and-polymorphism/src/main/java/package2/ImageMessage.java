package package2;

public class ImageMessage extends BaseMessage {
    private String imageUrl;

    public ImageMessage(String date, String author, String imageUrl) {
        super(date, author);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String render() {
        return "[" + date + "] " + author + " sent image: " + imageUrl;
    }
}
