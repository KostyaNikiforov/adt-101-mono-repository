package package2;

public class VoiceMessage extends BaseMessage {
    private int durationSeconds;

    public VoiceMessage(String date, String author, int durationSeconds) {
        super(date, author);
        this.durationSeconds = durationSeconds;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    @Override
    public String render() {
        return "[" + date + "] " + author + " sent voice message (" + durationSeconds + " sec)";
    }
}
