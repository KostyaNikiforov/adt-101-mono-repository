package auk;

public abstract class AUKPerson implements Comparable<AUKPerson> {
    private String name;
    private String id;
    private String email;

    public AUKPerson(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String getRole();

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public int compareTo(AUKPerson other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return getRole() + ": name=" + name + ", id=" + id + ", email=" + email;
    }
}
