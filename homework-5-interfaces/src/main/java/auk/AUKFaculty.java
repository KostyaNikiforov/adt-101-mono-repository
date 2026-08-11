package auk;

public class AUKFaculty extends AUKPerson {
    private String department;
    private String rank;

    public AUKFaculty(String name, String id, String email, String department, String rank) {
        super(name, id, email);
        this.department = department;
        this.rank = rank;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    @Override
    public String toString() {
        return super.toString() + ", Department: " + department + ", Rank: " + rank;
    }
}
