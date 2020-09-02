package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BaseHashMap<K, V> implements Iterable<V> {

    private Node<K, V>[] array;
    private float threshold;
    private int size = 0;

    public BaseHashMap() {
        this.array = new Node[16];
        this.threshold = array.length * 0.75f;
    }

    public boolean insert(K key, V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayDoubling();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (array[index] == null) {
            array[index] = newNode;
            size++;
            return true;
        }
        if (key.equals(array[index].getKey())) {
            array[index].setValue(value);
        }
        return false;
    }

    private void arrayDoubling() {
        Node<K, V>[] oldArray = array;
        array = new Node[oldArray.length * 2];
        size = 0;
        for (Node<K, V> el:
             oldArray) {
            if (el != null) {
                insert(el.getKey(), el.getValue());
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        if (array[index] == null) {
            return null;
        }
        if (key.equals(array[index].getKey())) {
            return array[index].getValue();
        }
        return null;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (array[index] == null) {
            return false;
        }
        if (key.equals(array[index].getKey())) {
            array[index] = null;
            size--;
            return true;
        }
        return false;
    }

    private int hash(K key) {
        return key.hashCode() % array.length;
    }

    @Override
    public Iterator<V> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<V>{
        private int countKey = 0;
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return countKey != size;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            for (int i = countKey; i < array.length; i++) {
                if (array[i] != null) {
                    countKey = i;
                }
            }
            return array[countKey].getValue();
        }
    }

    private class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
