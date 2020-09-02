package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private int inSize = 0;
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outSize = 0;

    public T poll() {
        T rsl = null;
        if (inSize == 0) {
            throw new NoSuchElementException();
        }
        while (true) {
            if (inSize > 1) {
                out.push(in.pop());
                outSize++;
                inSize--;
                continue;
            } else if (inSize == 1) {
                rsl = in.pop();
                inSize--;
            }
            if (outSize > 0) {
                in.push(out.pop());
                outSize--;
                continue;
            }
            return rsl;
        }
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
