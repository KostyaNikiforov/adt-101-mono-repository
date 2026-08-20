package auk;

public class AUKStaff extends AUKPerson {
    private String office;
    private String position;

    public AUKStaff(String name, String id, String email, String office, String position) {
        super(name, id, email);
        this.office = office;
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void work() {
        System.out.println(getName() + " works as " + position + " in " + office);
    }

    @Override
    public String getRole() {
        return "Staff";
    }

    @Override
    public String toString() {
        return super.toString() + ", office=" + office + ", position=" + position;
    }
}
