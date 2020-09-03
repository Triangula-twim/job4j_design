package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", file.getAbsoluteFile()));
        }
        System.out.println("name: " + file.getName() + " size: " + file.length());
    }
}
