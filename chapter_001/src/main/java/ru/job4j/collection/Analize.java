package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        Map<Integer, String> mapCurrent = current.stream()
                .collect(Collectors.toMap(key -> key.id, value -> value.name));
        for (User u: previous) {
            if (mapCurrent.containsKey(u.id)) {
                if (!mapCurrent.get(u.id).equals(u.name)) {
                    rsl.setChanged(rsl.getChanged() + 1);
                }
                mapCurrent.remove(u.id);
            } else {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }
        rsl.setAdded(mapCurrent.size());
        return rsl;
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
        private int added;
        private int changed;
        private int deleted;

        public void setAdded(int added) {
            this.added = added;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        public int getDeleted() {
            return deleted;
        }

        public int getChanged() {
            return changed;
        }
    }
}
