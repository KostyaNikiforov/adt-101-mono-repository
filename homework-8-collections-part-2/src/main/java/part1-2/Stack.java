package part2;

import list.LinkedList;

import java.util.NoSuchElementException;

public class Stack<T> {
    private final LinkedList<T> list = new LinkedList<>();

    public void push(T item) {
        list.addFirst(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = list.get(0);
        list.remove(0);
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.getSize() == 0;
    }

    public int size() {
        return list.getSize();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
