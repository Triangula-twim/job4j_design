package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader stream = new BufferedReader(new FileReader("log.txt"))) {
            List<String> lines = new ArrayList<String>();
            stream.lines().forEach(lines::add);
            for (String s: lines) {
                String[] strings = s.split(" ");
                if (strings[strings.length - 2].equals("404")) {
                    rsl.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            StringBuilder builder = new StringBuilder();
            log.forEach(s -> builder.append(s).append(System.lineSeparator()));
            out.write(builder.toString());
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s: log) {
            System.out.println(s);
        }
        save(log, "404.txt");
    }
}
