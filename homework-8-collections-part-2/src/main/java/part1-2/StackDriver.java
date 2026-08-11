package part2;

public class StackDriver {
    public static void main(String[] args) {
        part2.Stack<Integer> stack = new part2.Stack<>();

        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("after push 10, 20, 30: " + stack);
        System.out.println("size = " + stack.size());
        System.out.println("isEmpty = " + stack.isEmpty());

        System.out.println("peek = " + stack.peek());
        System.out.println("after peek: " + stack);

        System.out.println("pop = " + stack.pop());
        System.out.println("after pop: " + stack);

        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("after all pops: " + stack);
        System.out.println("isEmpty = " + stack.isEmpty());
        System.out.println("size = " + stack.size());

        stack.push(100);
        stack.push(200);
        System.out.println("push 100, 200: " + stack);
        System.out.println("peek = " + stack.peek());
        System.out.println("pop = " + stack.pop());
        System.out.println("final: " + stack);
    }
}
