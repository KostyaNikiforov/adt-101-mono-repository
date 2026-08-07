package package3;

public class Package3Demo {
    public static void main(String[] args) {
        // 2.1.4. Create a variable of the superclass type and assign a new instance of the subclass.
        PolySuper a = new PolySub();

        // 2.1.5. Call theMethod for this object.
        a.theMethod();

        // 2.1.6. The method of the subclass will be called because Java uses dynamic binding at runtime.
        // Even though the reference type is PolySuper, the actual object is PolySub, so the overridden method runs.
    }
}
