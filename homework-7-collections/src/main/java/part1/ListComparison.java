package part1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListComparison {
    public static void main(String[] args) {
        int numElements = 100000;
        DecimalFormat formatter = new DecimalFormat("#,###");

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            arrayList.add(0, i);
        }
        long endTime = System.nanoTime();
        System.out.println("ArrayList insertion into beginning time: " + formatter.format(endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            linkedList.add(0, i);
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList insertion into beginning time: " + formatter.format(endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            arrayList.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList access by index time: " + formatter.format(endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            linkedList.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList access by index time: " + formatter.format(endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            arrayList.remove(0);
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList remove first element time: " + formatter.format(endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            linkedList.remove(0);
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList remove first element time: " + formatter.format(endTime - startTime) + " ns");
    }
}
