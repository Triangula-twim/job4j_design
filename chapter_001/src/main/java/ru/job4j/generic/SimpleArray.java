package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private int size;
    private int countFullCells = 0;
    private Object[] objects;

    public SimpleArray(int size) {
        this.objects = new Object[size];
        this.size = size;
    }

    public void add(T model) {
        if (model != null) {
            this.objects[countFullCells++] = model;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, countFullCells);
        if (model != null) {
            this.objects[index] = model;
        }
    }

    public void remove(int index) {
        Objects.checkIndex(index, countFullCells);
        System.arraycopy(objects, index + 1, objects, index, size - 1 - index);
        objects[size - 1] = null;
        countFullCells--;
    }

    public T get(int index) {
        Objects.checkIndex(index, countFullCells);
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

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
            return (T) objects[cursor++];
        }
    }
}
