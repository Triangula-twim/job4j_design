package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(parent).isPresent()) {
            Node<E> mainNode = findBy(parent).get();
            if (findBy(child).isEmpty()) {
                mainNode.children.add(new Node<>(child));
                return true;
            }
        }
        return false;
    }

    public boolean isBinary() {
        return checkBinary(this.root);
    }

    public boolean checkBinary(Node<E> mainNode) {
        boolean rsl = false;
        if (mainNode.children.size() <= 2) {
            rsl = true;
            for (Node<E> el:
                    mainNode.children) {
                if (!checkBinary(el)) {
                    return false;
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
