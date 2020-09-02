package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 10;


    private Object[] elements;
    private int size;
    private int capacity;


    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
        this.capacity = INITIAL_SIZE;

    }

    @Override
    public boolean add(E element) {
        if (this.size == capacity) {
            grow();
        }
        this.elements[this.size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        ensureIndex(index);
        insert(index, element);
        return true;
    }


    @Override
    public E get(int index) {
        if (validIndex(index)) {
            throw new IndexOutOfBoundsException();
        } else {
            return (E) this.elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        ensureIndex(index);
        Object existing = this.elements[index];
        this.elements[index] = element;

        return (E) existing;
    }

    @Override
    public E remove(int index) {

        ensureIndex(index);
        E element = get(index);
        this.size--;
        shiftLeft(index);
        shrink();
        return (E) element;
    }

    private void shrink() {
        if (this.size < this.capacity / 2) {
            capacity /= 2;
            this.elements = Arrays.copyOf(this.elements, this.elements.length / 2);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0 ;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void grow() {
        capacity *= 2;
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }


    private void shiftLeft(int index) {

        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void ensureIndex(int index) {
        if (validIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean validIndex(int index) {
        return index < 0 || index > size - 1;
    }

    private void insert(int index, E element) {
        if (this.size == capacity) {
            grow();
        }
        E lastElement = this.get(this.size - 1);

        for (int i = this.size - 1; i >= index; i--) {
            elements[i] = this.elements[i - 1];
        }
        this.elements[this.size] = lastElement;
        this.elements[index] = element;
        this.size++;
    }
}