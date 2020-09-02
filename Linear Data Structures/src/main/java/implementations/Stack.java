package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private static class Node<E> {

        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

    }

    private Node<E> top;

    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newElement = new Node<>(element);
        newElement.next = top;
        top = newElement;
        size++;
    }

    @Override
    public E pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        Node<E> tmp = this.top;
        this.top = tmp.next;
        size--;
        return tmp.value;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.top.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = top;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                E value = this.node.value;
                this.node = this.node.next;
                return value;
            }
        };
    }
}
