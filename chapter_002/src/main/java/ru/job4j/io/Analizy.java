package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String[] cell = new String[2];
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] rsl = currentLine.split(" ");
                if (cell[0] == null) {
                    if (rsl[0].equals("500") || rsl[0].equals("400")) {
                        cell[0] = rsl[1];
                    }
                } else if (cell[1] == null) {
                    if (rsl[0].equals("200") || rsl[0].equals("300")) {
                        cell[1] = rsl[1];
                        list.add(cell[0] + ";" + cell[1] + "\n");
                        cell = new String[2];
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (String s: list) {
                out.write(s);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
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
