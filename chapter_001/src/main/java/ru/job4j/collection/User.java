package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User first = new User("Ivan", 1, new GregorianCalendar(1993, Calendar.APRIL, 24));
        User second = new User("Ivan", 1, new GregorianCalendar(1993, Calendar.APRIL, 24));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
        map.forEach((user, o) -> System.out.println(user.hashCode()));
        System.out.println(first.equals(second));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }
}
