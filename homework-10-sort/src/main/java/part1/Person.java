package part1;

public class Person implements Comparable<Person> {
    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public int compareTo(Person other) {
        return this.birthYear - other.birthYear;
    }

    @Override
    public String toString() {
        return name + " (" + birthYear + ")";
    }
}
