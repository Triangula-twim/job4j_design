package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                builder.append((char) read);
            }
            String[] strings = builder.toString().split(System.lineSeparator());
            for (String s:
                 strings) {
                int rsl = Integer.parseInt(s);
                if(rsl % 2 == 0) {
                    System.out.println(rsl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
