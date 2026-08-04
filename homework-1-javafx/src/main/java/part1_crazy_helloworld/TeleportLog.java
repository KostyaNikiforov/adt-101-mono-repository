package part1_crazy_helloworld;

import javafx.scene.control.TextArea;

public class TeleportLog extends TextArea {
    private String lastValue = "";

    public TeleportLog() {
        setEditable(false);
        setPrefRowCount(7);
        setWrapText(true);
        setPromptText("Here the travel history of your value will appear...");
    }

    public void write(String station, String value) {
        lastValue = value;
        appendText(station + " >>> " + value + "\n");
    }

    public String getLastValue() {
        return lastValue;
    }

    public void clear() {
        super.clear();
        lastValue = "";
    }
}
