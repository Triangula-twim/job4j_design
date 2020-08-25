package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainerTest {

    @Test
    public void getOne() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        container.add("2");
        container.add("3");
        Assert.assertEquals("3", container.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOneThrow() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        container.add("2");
        container.add("3");
        container.get(3);
    }

    @Test
    public void addOne() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        Assert.assertEquals("1", container.get(0));
    }

    @Test
    public void hasNextOneTrue() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        Iterator<String> iterator = container.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void hasNextOneFalse() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add(null);
        Iterator<String> iterator = container.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void nextOne() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        container.add("2");
        container.add("3");
        Iterator<String> iterator = container.iterator();
        iterator.next();
        iterator.next();
        Assert.assertEquals("3", iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextOneThrow() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        Iterator<String> iterator = container.iterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void nextTwoThrow() {
        DynamicContainer<String> container = new DynamicContainer<String>();
        container.add("1");
        Iterator<String> iterator = container.iterator();
        iterator.next();
        container.add("2");
        iterator.next();
    }
}