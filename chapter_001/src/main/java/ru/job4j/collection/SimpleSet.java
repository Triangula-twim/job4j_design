package ru.job4j.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> array = new SimpleArray<E>();
    private Map<E, Object> map = new LinkedHashMap<>();

    public void add(E e) {
        if (!map.containsKey(e)) {
            array.add(e);
            map.put(e, new Object());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
