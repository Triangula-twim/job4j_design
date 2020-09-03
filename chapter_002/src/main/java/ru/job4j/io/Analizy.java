package ru.job4j.io;

import javax.swing.plaf.IconUIResource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .filter(s -> !s.equals(""))
                    .map(s -> s.split(" "))
                    .forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            out.write(notWork(list));
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    private String notWork(List<String[]> list) {
        StringBuilder rsl = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0].equals("500") || list.get(i)[0].equals("400")) {
                rsl.append(list.get(i)[1]).append(";");
                i++;
                for (int j = i; j < list.size(); j++) {
                    if (!list.get(j)[0].equals("500") && !list.get(j)[0].equals("400")) {
                        rsl.append(list.get(j)[1]);
                        break;
                    } else {
                        i++;
                    }
                }
                rsl.append("\n");
            }
        }
        return rsl.toString();
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
