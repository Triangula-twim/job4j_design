package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int size;
    private int modCount = 0;
    private int countFullCells = 0;

    public SimpleArray() {
        this.size = 1;
        this.container = new Object[1];
    }

    public T get(int index) {
        Objects.checkIndex(index, countFullCells);
        return (T) this.container[index];
    }

    public void add(T model) {
        if (model != null) {
            modCount++;
            if (countFullCells >= size) {
                size++;
                container = Arrays.copyOf(container, size);
            }
            this.container[countFullCells++] = model;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        private int expectedModCount = modCount;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < countFullCells;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return (T) container[cursor++];
        }
    }
}
