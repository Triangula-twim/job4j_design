package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteFirstLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenMultiDeleteT() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        int rsl = linked.deleteFirst();
        assertThat(rsl, is(1));
    }

    @Test
    public void whenMultiDeleteLast() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.deleteLast();
        Iterator<Integer> it = linked.iterator();
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMultiDeleteLastOne() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteLast();
        Iterator<Integer> it = linked.iterator();
        it.next();
    }
}