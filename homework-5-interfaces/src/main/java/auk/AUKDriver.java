package auk;

import java.util.ArrayList;
import java.util.Collections;

public class AUKDriver {
    public static void main(String[] args) {
        ArrayList<AUKPerson> people = new ArrayList<>();

        people.add(new AUKStudent("Maria Petrova", "S001", "maria@auk.edu", "Computer Science", 2, 3.8));
        people.add(new AUKStudent("Ivan Shevchenko", "S002", "ivan@auk.edu", "Business", 3, 3.5));

        people.add(new AUKFaculty("Olena Bondar", "F001", "olena@auk.edu", "Computer Science", "Professor"));
        people.add(new AUKFaculty("Andriy Koval", "F002", "andriy@auk.edu", "Mathematics", "Associate Professor"));

        people.add(new AUKStaff("Natalia Melnyk", "ST001", "natalia@auk.edu", "Admin Building", "Registrar"));
        people.add(new AUKStaff("Dmytro Hrytsenko", "ST002", "dmytro@auk.edu", "Library", "Librarian"));

        System.out.println("Before sorting:");
        for (AUKPerson person : people) {
            System.out.println(person);
        }

        Collections.sort(people);

        System.out.println();
        System.out.println("After sorting:");
        for (AUKPerson person : people) {
            System.out.println(person);
        }
    }
}
