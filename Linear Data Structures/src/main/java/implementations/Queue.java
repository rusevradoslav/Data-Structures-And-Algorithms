package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private int size;

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        this.size++;
    }

    @Override
    public E poll() {
        if (this.head == null) {
            throw new IllegalStateException();
        }
        Node<E> currentNode = this.head;
        this.head = currentNode.next;
        size--;
        return currentNode.value;
    }

    @Override
    public E peek() {
        if (this.head == null) {
            throw new IllegalStateException();
        }
        Node<E> currentNode = this.head;
        return currentNode.value;
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

            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return this.node != null;
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
