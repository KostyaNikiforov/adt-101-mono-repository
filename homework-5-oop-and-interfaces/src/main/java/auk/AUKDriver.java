package auk;

import java.util.ArrayList;
import java.util.Collections;

public class AUKDriver {
    public static void main(String[] args) {
        ArrayList<AUKPerson> people = new ArrayList<>();

        people.add(new AUKStudent("Boris Lee", "S1002", "boris@auk.edu", "Business", 3, 3.2));
        people.add(new AUKFaculty("David Cho", "F2002", "david@auk.edu", "Mathematics", "Lecturer"));
        people.add(new AUKStaff("Frank Oh", "W3002", "frank@auk.edu", "IT Support", "Technician"));
        people.add(new AUKStudent("Anna Kim", "S1001", "anna@auk.edu", "Computer Science", 2, 3.6));
        people.add(new AUKFaculty("Clara Park", "F2001", "clara@auk.edu", "Computer Science", "Professor"));
        people.add(new AUKStaff("Elena Han", "W3001", "elena@auk.edu", "Admissions", "Coordinator"));

        System.out.println("Before sorting:");
        for (AUKPerson person : people) {
            person.printInfo();
        }

        Collections.sort(people);

        System.out.println();
        System.out.println("After sorting:");
        for (AUKPerson person : people) {
            person.printInfo();
        }
    }
}
