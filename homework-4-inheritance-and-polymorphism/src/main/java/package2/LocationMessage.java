package package2;

public class LocationMessage extends BaseMessage {
    private double latitude;
    private double longitude;

    public LocationMessage(String date, String author, double latitude, double longitude) {
        super(date, author);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String render() {
        return "[" + date + "] " + author + " shared location: " + latitude + ", " + longitude;
    }
}
