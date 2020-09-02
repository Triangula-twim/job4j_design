package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BaseHashMapTest {

    @Test
    public void getOne() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        map.insert(2, "two");
        Assert.assertEquals(map.get(2), "two");
    }

    @Test
    public void insertOne() {
        BaseHashMap map = new BaseHashMap();
        Assert.assertTrue(map.insert(1, "one"));
    }

    @Test
    public void insertTwo() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        map.insert(1, "two");
        Assert.assertEquals(map.get(1), "two");
    }

    @Test
    public void deleteOne() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        map.insert(2, "two");
        map.delete(2);
        Assert.assertNull(map.get(2));
    }

    @Test
    public void deleteTwo() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        map.insert(2, "two");
        map.delete(1);
        Assert.assertNull(map.get(1));
    }

    @Test
    public void iteratorHasNextTrue() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorDeleteNext() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        map.delete(1);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextNext() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void iteratorNext() {
        BaseHashMap map = new BaseHashMap();
        map.insert(1, "one");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }
}