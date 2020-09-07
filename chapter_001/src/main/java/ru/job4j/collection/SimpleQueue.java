package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private int inSize = 0;
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outSize = 0;

    public T poll() {
        T rsl = null;
        if (outSize == 0) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
            }
            rsl = out.pop();
            inSize--;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
