package part1_class_c;

public class C {
    public static final int CONST1 = 100;

    private int x;
    private int y;
    private String label;

    public C() {
        this(0, 0, "");
    }

    public C(int x, int y, String label) {
        setX(x);
        setY(y);
        setLabel(label);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private String getZ() {
        return label;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void setLabel(String label) {
        this.label = label;
    }
}
