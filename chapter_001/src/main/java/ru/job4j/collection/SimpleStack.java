package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int sizeField = 0;

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }

    public boolean isEmpty() {
        return sizeField == 0;
    }

    public int getSizeField() {
        return sizeField;
    }

    public void setSizeField(int sizeField) {
        this.sizeField = sizeField;
    }
}
