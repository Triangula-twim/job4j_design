package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> node = new Node<T>(last, value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        last.next = node;
        last = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        head = head.next;
        return rsl;
    }

    public T deleteLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = last;
        if (last == head) {
            last = null;
            head = null;
        } else {
            last = last.prev;
            node.prev = null;
        }
        return node.value;
    }

    public void revert() {
        Node<T> prevElement = null;
        Node<T> currentElement = head;
        Node<T> nextElement = null;
        while (currentElement != null) {
            nextElement = currentElement.next;
            currentElement.next = prevElement;
            prevElement = currentElement;
            currentElement = nextElement;
        }
        head = prevElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
