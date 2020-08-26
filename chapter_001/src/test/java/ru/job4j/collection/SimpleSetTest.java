package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddThenIter() {
        SimpleSet<Integer> set = new SimpleSet<Integer>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddSameThenIter() {
        SimpleSet<Integer> set = new SimpleSet<Integer>();
        set.add(1);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        it.next();
    }
}