package package1;

public class SuperClass extends MyAbstractClass {
    private int privateField = 10;
    protected int protectedField = 20;
    public int publicField = 30;

    private String name;

    public SuperClass() {
        name = "default";
    }

    public SuperClass(String name) {
        this.name = name;
    }

    public void showInfo() {
        System.out.println("SuperClass name: " + name);
    }

    public void showInfo(int number) {
        System.out.println("SuperClass number: " + number);
    }

    public void greet() {
        System.out.println("Hello from SuperClass");
    }

    public int getProtectedField() {
        return protectedField;
    }

    public int getPublicField() {
        return publicField;
    }

    @Override
    public String describe() {
        return "SuperClass description";
    }
}
