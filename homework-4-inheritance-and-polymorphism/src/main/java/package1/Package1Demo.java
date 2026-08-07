package package1;

public class Package1Demo {
    public static void main(String[] args) {
        // 1.1.1. Create a superclass, subclass and an instance of the subclass.
        SubClass sub = new SubClass("Student");

        // 1.1.2. Create instance of subclass while declaring it as type of superclass.
        // It is not an error because SubClass IS-A SuperClass. A reference of parent type can point to a child object.
        SuperClass a = new SubClass("Polymorphic reference");
        System.out.println(a.getClass());

        // 1.1.3. Instance variables with different access modifiers and access from child class.
        sub.showChildInfo();
        // privateField from SuperClass is not visible here in SubClass, so we cannot write sub.privateField.

        // 1.1.4. Overloaded methods in the same class and in inherited class.
        sub.showInfo();
        sub.showInfo(5);
        sub.showInfo("overload in child");

        // 1.1.5. Overridden methods.
        sub.greet();

        // 1.1.6. The use of super keyword to call overridden method of the superclass inside overriding method.
        sub.greetWithSuper();

        // 1.1.7. The use of super keyword for parent constructor call.
        SubClass defaultSub = new SubClass();
        SubClass namedSub = new SubClass("Named");
        defaultSub.showInfo();
        namedSub.showInfo();

        // 1.1.8. Abstract classes and methods.
        System.out.println(sub.describe());
        SuperClass another = new SubClass("Abstract demo");
        System.out.println(another.describe());
    }
}
