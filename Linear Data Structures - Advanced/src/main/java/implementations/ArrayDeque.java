package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private static final int INITIAL_CAPACITY = 7;

    private int head;
    private int tail;
    private Object[] elements;

    private int size;

    public ArrayDeque() {
        this.elements = new Object[INITIAL_CAPACITY];
        int middle = INITIAL_CAPACITY / 2;
        this.head = this.tail = middle;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if (isEmpty()) {
            this.elements[head] = element;
        } else {
            if (this.head == 0) {
                this.elements = grow();
            }
            this.elements[--this.head] = element;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        if (isEmpty()) {
            this.elements[head] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                this.elements = grow();
            }
            this.elements[++this.tail] = element;
        }

        this.size++;
    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.head + index;
        this.ensureIndex(realIndex);
        if (realIndex - this.head < this.tail - realIndex) {
            insertAndShiftLeft(realIndex - 1, element);
        } else {
            insertAndShiftRight(realIndex, element);
        }
    }

    private void insertAndShiftRight(int index, E element) {
        E lastAElement = this.getAt(this.tail);
        for (int i = this.tail; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        this.addLast(lastAElement);
    }

    private void insertAndShiftLeft(int index, E element) {
        E firstElement = this.getAt(this.head);
        for (int i = this.head; i < index; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[index] = element;
        this.addFirst(firstElement);
    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.head + index;
        ensureIndex(realIndex);
        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {
        if (this.size != 0) {
            return getAt(this.head);
        }
        return null;
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E get(int index) {

        int realIndex = this.head + index;
        this.ensureIndex(realIndex);
        return getAt(realIndex);
    }

    @Override
    public E get(Object object) {
        if (!isEmpty()) {
            for (int i = head; i <= tail; i++) {
                if (elements[i].equals(object)) {
                    return getAt(i);
                }
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        int realIndex = this.head + index;
        this.ensureIndex(realIndex);
        E element = getAt(realIndex);
        for (int j = realIndex; j < this.tail; j++) {
            this.elements[j] = this.elements[j + 1];
        }
        this.removeLast();
        return element;
    }

    @Override
    public E remove(Object object) {
        if (!isEmpty()) {
            for (int i = this.head; i <= this.tail; i++) {
                if (this.elements[i].equals(object)) {
                    E element = getAt(i);
                    this.elements[i] = null;

                    for (int j = i; j < this.tail; j++) {
                        this.elements[j] = this.elements[j + 1];
                    }
                    this.removeLast();
                    return element;
                }

            }
        }
        return null;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            E element = getAt(this.head);
            this.elements[this.head++] = null;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            E element = getAt(this.tail);
            this.elements[this.tail--] = null;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[this.size];

        int index = 0;
        for (int i = head; i <= this.tail; i++) {
            newElements[index++] = this.elements[i];
        }

        this.elements = newElements;

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int index = head;

            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return getAt(index++);
            }
        };
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2 + 1;

        Object[] newElements = new Object[newCapacity];

        int middle = newElements.length / 2;

        int begin = middle - this.size / 2;

        int index = head;
        for (int i = begin; index <= this.tail; i++) {
            newElements[i] = this.elements[index++];
        }

        this.head = begin;
        this.tail = this.head + this.size - 1;
        return newElements;
    }

    private E getAt(int index) {
        return (E) this.elements[index];
    }

    private void ensureIndex(int realIndex) {
        if (realIndex < head || realIndex > tail) {
            throw new IndexOutOfBoundsException("Index out of bound for index: " + (realIndex - head));
        }
    }
}
