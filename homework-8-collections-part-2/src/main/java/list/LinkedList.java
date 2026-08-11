package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T e) {
        linkLast(e);
    }

    public void add(int i, T e) {
        checkPositionIndex(i);
        if (i == size) {
            linkLast(e);
        } else {
            linkBefore(e, getNode(i));
        }
    }

    public T get(int i) {
        checkElementIndex(i);
        return getNode(i).item;
    }

    public void remove(T e) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (Objects.equals(e, x.item)) {
                unlink(x);
                return;
            }
        }
    }

    public void remove(int i) {
        checkElementIndex(i);
        unlink(getNode(i));
    }

    public void removeAll(T e) {
        Node<T> x = first;
        while (x != null) {
            Node<T> next = x.next;
            if (Objects.equals(e, x.item)) {
                unlink(x);
            }
            x = next;
        }
    }

    public void addFirst(T e) {
        linkFirst(e);
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> x = first;
        for (int i = 0; i < size; i++) {
            sb.append(x.item);
            if (i < size - 1) {
                sb.append(", ");
            }
            x = x.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<T> getNode(int index) {
        Node<T> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void linkFirst(T e) {
        Node<T> f = first;
        Node<T> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(T e) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void linkBefore(T e, Node<T> succ) {
        Node<T> pred = succ.prev;
        Node<T> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    private void unlink(Node<T> x) {
        Node<T> next = x.next;
        Node<T> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> next = first;
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> current = next;
            next = current.next;
            nextIndex++;
            return current.item;
        }
    }
}
