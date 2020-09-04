package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "js").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SimpleFileVisitor visitor = new SimpleFileVisitor();
        visitor.setExt(ext);
        Files.walkFileTree(root, visitor);
        return visitor.getPath();
    }

    public static class SimpleFileVisitor implements FileVisitor<Path> {

        private List<Path> path = new ArrayList<>();

        private String ext;

        public void setExt(String ext) {
            this.ext = ext;
        }

        public List<Path> getPath() {
            return path;
        }

        @Override
        public FileVisitResult preVisitDirectory(
                Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(
                Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(ext)) {
                this.path.add(file.toAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(
                Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(
                Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

    }
}
