package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addLast(E element) {
        System.out.println();
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
        size++;
    }

    @Override
    public void addFirst(E element) {
        System.out.println();
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        size++;
    }

    @Override
    public E removeFirst() {

        if (this.head == null) {
            throw new IllegalStateException();
        }
        Node<E> currentNode = this.head;
        this.head = currentNode.next;

        size--;
        return currentNode.value;
    }

    @Override
    public E removeLast() {
        System.out.println();
        if (this.head == null) {
            throw new IllegalStateException();
        }

        Node<E> currentNode = this.head;
        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }
        E value = currentNode.next.value;
        currentNode.next = null;
        size--;
        return value;
    }

    @Override
    public E getFirst() {
        if (this.head == null) {
            throw new IllegalStateException();
        }

        return this.head.value;
    }

    @Override
    public E getLast() {
        System.out.println();
        if (this.head == null) {
            throw new IllegalStateException();
        }

        Node<E> currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                E value = node.value;
                this.node = this.node.next;
                return value;
            }
        };
    }
}
