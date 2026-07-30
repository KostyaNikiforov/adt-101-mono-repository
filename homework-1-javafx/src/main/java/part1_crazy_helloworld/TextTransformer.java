package part1_crazy_helloworld;

public class TextTransformer {
    public static final String AS_IS = "As is";
    public static final String UPPER = "UPPERCASE";
    public static final String REVERSED = "Reversed";
    public static final String SPACED = "S p a c e d";

    private String mode = AS_IS;

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public String transform(String value) {
        switch (mode) {
            case UPPER:
                return value.toUpperCase();
            case REVERSED:
                return new StringBuilder(value).reverse().toString();
            case SPACED:
                return String.join(" ", value.split(""));
            default:
                return value;
        }
    }
}
