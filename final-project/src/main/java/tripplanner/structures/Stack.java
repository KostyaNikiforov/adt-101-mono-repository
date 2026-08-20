package tripplanner.structures;

import java.util.NoSuchElementException;

public class Stack<T> {
    private final LinkedList<T> list = new LinkedList<>();

    public void push(T item) {
        list.add(0, item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = list.get(0);
        list.remove(0);
        return item;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.getSize();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
