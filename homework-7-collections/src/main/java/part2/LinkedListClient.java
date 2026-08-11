package part2;

public class LinkedListClient {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        System.out.println("=== add(T e) ===");
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);
        System.out.println("size = " + list.getSize());

        System.out.println("=== addFirst(T e) ===");
        list.addFirst("Z");
        System.out.println(list);

        System.out.println("=== add(int i, T e) ===");
        list.add(2, "X");
        System.out.println(list);

        System.out.println("=== get(int i) ===");
        System.out.println("get(0) = " + list.get(0));
        System.out.println("get(2) = " + list.get(2));
        System.out.println("get(4) = " + list.get(4));

        System.out.println("=== remove(int i) ===");
        list.remove(2);
        System.out.println(list);

        System.out.println("=== remove(T e) ===");
        list.remove("B");
        System.out.println(list);

        System.out.println("=== removeAll(T e) ===");
        list.add("A");
        list.add("A");
        list.add("D");
        System.out.println("before removeAll: " + list);
        list.removeAll("A");
        System.out.println("after removeAll: " + list);

        System.out.println("=== iterator ===");
        for (String value : list) {
            System.out.println(value);
        }

        System.out.println("=== getSize() ===");
        System.out.println("size = " + list.getSize());

        System.out.println("=== empty list ===");
        LinkedList<Integer> numbers = new LinkedList<>();
        System.out.println(numbers);
        System.out.println("size = " + numbers.getSize());
        numbers.addFirst(10);
        numbers.add(20);
        numbers.add(1, 15);
        System.out.println(numbers);
        numbers.remove(Integer.valueOf(15));
        System.out.println(numbers);
    }
}
