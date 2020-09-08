package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgZip {
    private final Map<String, String> argsMap = new HashMap<>();

    public ArgZip(String[] args) {
        Arrays.stream(args)
                .filter(s -> !s.equals(""))
                .filter(s -> s.contains("="))
                .map(s -> s.split("="))
                .filter(strings -> strings.length == 2)
                .forEach(strings ->
                        this.argsMap.put(strings[0].replace("-", ""), strings[1]));
    }

    public boolean valid() {
        if (this.argsMap.size() != 3) {
            throw new IllegalArgumentException("Some parameters are not defined");
        }
        return true;
    }

    public String directory() {
        return this.argsMap.get("d");
    }

    public String exclude() {
        return this.argsMap.get("e").replace("*", "");
    }

    public String output() {
        return this.argsMap.get("o");
    }
}
