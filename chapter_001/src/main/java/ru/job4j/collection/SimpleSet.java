package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> array = new SimpleArray<E>();

    public void add(E e) {
        if (!checkCell(e)) {
            array.add(e);
        }
    }

    public boolean checkCell(E e) {
        for (E el:
                array) {
            if (Objects.equals(el, e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
