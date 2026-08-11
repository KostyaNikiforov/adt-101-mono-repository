package auk;

public class AUKStudent extends AUKPerson {
    private String major;
    private int year;
    private double gpa;

    public AUKStudent(String name, String id, String email, String major, int year, double gpa) {
        super(name, id, email);
        this.major = major;
        this.year = year;
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() + ", Major: " + major + ", Year: " + year + ", GPA: " + gpa;
    }
}
