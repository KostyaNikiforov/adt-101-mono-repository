package part2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {
    private Node<T> root;

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        private Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T rootData) {
        this.root = new Node<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> left, BinaryTree<T> right) {
        this.root = new Node<>(rootData);
        if (left != null) {
            this.root.left = left.root;
        }
        if (right != null) {
            this.root.right = right.root;
        }
    }

    public void inorderTraverse() {
        inorderTraverse(root);
        System.out.println();
    }

    private void inorderTraverse(Node<T> node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.print(node.data + " ");
        inorderTraverse(node.right);
    }

    public void postorderTraverse() {
        postorderTraverse(root);
        System.out.println();
    }

    private void postorderTraverse(Node<T> node) {
        if (node == null) {
            return;
        }
        postorderTraverse(node.left);
        postorderTraverse(node.right);
        System.out.print(node.data + " ");
    }

    public void preorderTraverse() {
        preorderTraverse(root);
        System.out.println();
    }

    private void preorderTraverse(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorderTraverse(node.left);
        preorderTraverse(node.right);
    }

    public int getSize() {
        return getSizeOfNode(root);
    }

    private int getSizeOfNode(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSizeOfNode(node.left) + getSizeOfNode(node.right);
    }

    @Override
    public Iterator<T> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<T> {
        private final Stack<Node<T>> stack = new Stack<>();

        private InorderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.pop();
            pushLeft(node.right);
            return node.data;
        }
    }
}
