package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (true) {
            try {
                out.push(in.pop());
            } catch (NoSuchElementException e) {
                T rsl = out.pop();
                while (true) {
                    try {
                        in.push(out.pop());
                    } catch (NoSuchElementException en) {
                        break;
                    }
                }
                return rsl;
            }
        }
    }

    public void push(T value) {
        in.push(value);
    }
}
