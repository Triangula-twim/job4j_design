package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private int inSize = 0;
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outSize = 0;

    public T poll() {
        for (int i = 0; i < inSize; i++) {
            out.push(in.pop());
            outSize++;
        }
        inSize = 0;
        T rsl = out.pop();
        outSize--;
        for (int i = 0; i < outSize; i++) {
            in.push(out.pop());
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
