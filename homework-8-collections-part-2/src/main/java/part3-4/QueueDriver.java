package part3;

public class QueueDriver {
    public static void main(String[] args) {
        part3.Queue<String> queue = new part3.Queue<>();

        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println("after enqueue A, B, C: " + queue);
        System.out.println("size = " + queue.size());
        System.out.println("isEmpty = " + queue.isEmpty());

        System.out.println("peek = " + queue.peek());
        System.out.println("after peek: " + queue);

        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("after dequeue: " + queue);

        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("after all dequeues: " + queue);
        System.out.println("isEmpty = " + queue.isEmpty());
        System.out.println("size = " + queue.size());

        queue.enqueue("X");
        queue.enqueue("Y");
        System.out.println("enqueue X, Y: " + queue);
        System.out.println("peek = " + queue.peek());
        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("final: " + queue);
    }
}
