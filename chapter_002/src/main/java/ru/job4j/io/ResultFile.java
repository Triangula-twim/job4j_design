package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(multiple(5).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String multiple(int size) {
        int[][] table = new int[size][size];
        StringBuilder rsl = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (i + 1) * (j + 1);
                rsl.append((i + 1) * (j + 1)).append(" ");
                if (j == table[i].length - 1) {
                    rsl.append("\n");
                }
            }
        }
        return rsl.toString();
    }
}
