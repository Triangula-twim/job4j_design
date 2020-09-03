package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void twoBreakpoint() {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/log.txt")))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/log.txt", "./data/unavailable.txt");
        String expectation = "10:57:01;10:59:01" + System.lineSeparator()
                + "11:01:02;11:02:02" + System.lineSeparator();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./data/unavailable.txt"))) {
            reader.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectation, rsl.toString());
    }

    @Test
    public void oneBreakpoint() {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/log.txt")))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/log.txt", "./data/unavailable.txt");
        String expectation = "10:57:01;10:59:01" + System.lineSeparator();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./data/unavailable.txt"))) {
            reader.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectation, rsl.toString());
    }

    @Test
    public void noBreakpoint() {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/log.txt")))) {
            writer.println("200 10:56:01");
            writer.println("200 10:59:01");
            writer.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/log.txt", "./data/unavailable.txt");
        String expectation = "";
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./data/unavailable.txt"))) {
            reader.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectation, rsl.toString());
    }

    @Test
    public void dropNoBreakpoint() throws IOException {
        File source = folder.newFile("log.txt");
        File target = folder.newFile("unavailable.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("200 10:59:01");
            writer.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String expectation = "";
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(target))) {
            reader.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        Assert.assertEquals(expectation, rsl.toString());
    }

    @Test
    public void dropTwoBreakpoint() throws IOException {
        File source = folder.newFile("log.txt");
        File target = folder.newFile("unavailable.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String expectation = "10:57:01;10:59:01" + System.lineSeparator()
                + "11:01:02;11:02:02" + System.lineSeparator();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(target))) {
            reader.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        Assert.assertEquals(expectation, rsl.toString());
    }
}