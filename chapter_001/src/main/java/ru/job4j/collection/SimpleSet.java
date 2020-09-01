package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> array = new SimpleArray<E>();

    public void add(E e) {
        boolean sameCell = false;
        for (E el:
             array) {
            if (el.equals(e)) {
                sameCell = true;
                break;
            }
        }
        if (!sameCell) {
            array.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
