package ru.job4j.generic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void getOne() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(2);
        integers.add(1);
        integers.add(2);
        Integer expected = 2;
        Assert.assertEquals(expected, integers.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTwo() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(2);
        integers.add(1);
        integers.get(1);
    }

    @Test
    public void addOne() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(1);
        integers.add(12);
        Integer expected = 12;
        Assert.assertEquals(expected, integers.get(0));
    }

    @Test
    public void setOne() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(2);
        integers.add(1);
        integers.add(2);
        integers.set(0, 3);
        Integer expected = 3;
        Assert.assertEquals(expected, integers.get(0));
    }

    @Test
    public void removeOne() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(2);
        integers.add(1);
        integers.add(2);
        integers.remove(1);
        Integer[] check = {integers.get(0)};
        Integer[] expected = {1};
        Assert.assertArrayEquals(expected, check);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeThrow() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(4);
        integers.add(1);
        integers.add(2);
        integers.remove(2);
    }

    @Test
    public void removeTwo() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(3);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.remove(1);
        Integer[] check = {integers.get(0), integers.get(1)};
        Integer[] expected = {1, 3};
        Assert.assertArrayEquals(expected, check);
    }

    @Test
    public void hasNextOneTrue() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(3);
        integers.add(1);
        Iterator<Integer> iterator = integers.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void hasNextOneFalse() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(3);
        integers.add(null);
        Iterator<Integer> iterator = integers.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void nextOne() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(3);
        integers.add(1);
        Iterator<Integer> iterator = integers.iterator();
        Integer expected = 1;
        Assert.assertEquals(expected, iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextOneThrow() {
        SimpleArray<Integer> integers = new SimpleArray<Integer>(3);
        integers.add(1);
        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
    }
}