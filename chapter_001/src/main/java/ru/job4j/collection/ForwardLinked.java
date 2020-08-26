package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        if (head.next != null) {
            head.value = head.next.value;
            head.next = head.next.next;
        } else {
            head = null;
        }
        return rsl;
    }

    public T deleteLast() {
        Node<T> last = head;
        T rsl = last.value;
        if (head.next == null) {
            head = null;
            return rsl;
        }
        while (last.next != null) {
            if (last.next.next == null) {
                rsl = last.next.value;
                last.next = null;
                return rsl;
            }
            last = last.next;
        }
        return null;
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

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
