package ru.job4j.collection;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        for (User user:
             current) {
            int index = findById(user.id, previous);
            if (index == -1) {
                rsl.added += 1;
                continue;
            }
            rsl.changed += previous.get(index).name.equals(user.name) ? 0 : 1;
        }
        for (User user:
             previous) {
            int index = findById(user.id, current);
            rsl.deleted += index == -1 ? 1 : 0;
        }
        return rsl;
    }

    private int findById(int id, List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
