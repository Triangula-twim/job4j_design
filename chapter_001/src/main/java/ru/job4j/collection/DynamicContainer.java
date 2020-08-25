package ru.job4j.collection;

import java.util.*;

public class DynamicContainer<E> implements Iterable<E> {

    private Node<E> before;
    private Node<E> after;
    private int modCount = 0;
    private int countFullCells = 0;

    public void add(E value) {
        if (value != null) {
            Node<E> l = this.after;
            Node<E> newNode = new Node<>(l, value, null);
            this.after = newNode;
            if (l == null) {
                this.before = newNode;
            } else {
                l.next = newNode;
            }
            this.countFullCells++;
            this.modCount++;
        }
    }

    public E get(int index) {
        Objects.checkIndex(index, countFullCells);
        Node<E> rsl = this.before;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private int expectedModCount = modCount;
        private int cursor = 0;
        private Node<E> lastNode = before;

        @Override
        public boolean hasNext() {
            return cursor < countFullCells;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (cursor == 0) {
                cursor++;
                return lastNode.item;
            }
            cursor++;
            lastNode = lastNode.next;
            return lastNode.item;
        }
    }

    private static class Node<E> {

        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
