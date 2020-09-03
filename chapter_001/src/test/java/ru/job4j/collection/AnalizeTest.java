package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import ru.job4j.collection.Analize.User;
import ru.job4j.collection.Analize.Info;

public class AnalizeTest {

    @Test
    public void diffOneDeletedOneAdded() {
        Analize analize = new Analize();
        Info expected = new Info();
        expected.setDeleted(1);
        expected.setAdded(1);
        List<User> previous = List.of(new Analize.User(1, "Slava"),
                new Analize.User(2, "Ira"),
                new Analize.User(4, "Gleb"));
        List<User> current = List.of(new Analize.User(1, "Slava"),
                new Analize.User(2, "Ira"),
                new Analize.User(3, "Victor"));
        Assert.assertEquals(analize.diff(previous, current), expected);
    }

    @Test
    public void diffOneDeletedOneChangedTwoAdded() {
        Analize analize = new Analize();
        Info expected = new Info();
        expected.setDeleted(1);
        expected.setChanged(1);
        expected.setAdded(2);
        List<User> previous = List.of(new Analize.User(1, "Slava"),
                new Analize.User(4, "Gleb"));
        List<User> current = List.of(new Analize.User(1, "Sliva"),
                new Analize.User(2, "Ira"),
                new Analize.User(3, "Victor"));
        Assert.assertEquals(analize.diff(previous, current), expected);
    }
}