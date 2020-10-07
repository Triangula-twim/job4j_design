package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        int inSize = in.getSizeField();
        if (out.isEmpty()) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
                in.setSizeField(in.getSizeField() - 1);
                out.setSizeField(out.getSizeField() + 1);
            }
        }
        out.setSizeField(out.getSizeField() - 1);
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        in.setSizeField(in.getSizeField() + 1);
    }
}
