package part3;

import list.LinkedList;

import java.util.NoSuchElementException;

public class Queue<T> extends LinkedList<T> {
    public void enqueue(T item) {
        add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = get(0);
        remove(0);
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return get(0);
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int size() {
        return getSize();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
