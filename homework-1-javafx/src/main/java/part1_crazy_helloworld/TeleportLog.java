package part1_crazy_helloworld;

import javafx.scene.control.TextArea;

public class TeleportLog {
    private final TextArea area = new TextArea();
    private String lastValue = "";

    public TeleportLog() {
        area.setEditable(false);
        area.setPrefRowCount(7);
        area.setWrapText(true);
        area.setPromptText("Here the travel history of your value will appear...");
    }

    public TextArea getArea() {
        return area;
    }

    public void write(String station, String value) {
        lastValue = value;
        area.appendText(station + " >>> " + value + "\n");
    }

    public String getLastValue() {
        return lastValue;
    }

    public void clear() {
        area.clear();
        lastValue = "";
    }
}
