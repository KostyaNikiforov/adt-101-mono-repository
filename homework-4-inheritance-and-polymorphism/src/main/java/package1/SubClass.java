package package1;

public class SubClass extends SuperClass {

    public SubClass() {
        super();
    }

    public SubClass(String name) {
        super(name);
    }

    public void showChildInfo() {
        System.out.println("protectedField = " + protectedField);
        System.out.println("publicField = " + publicField);
    }

    public void showInfo(String text) {
        System.out.println("SubClass text: " + text);
    }

    @Override
    public void greet() {
        System.out.println("Hello from SubClass");
    }

    public void greetWithSuper() {
        super.greet();
        System.out.println("After super.greet()");
    }

    @Override
    public String describe() {
        return "SubClass description";
    }
}
