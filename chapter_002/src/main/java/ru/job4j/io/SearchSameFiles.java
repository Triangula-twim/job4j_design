package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchSameFiles {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            Path start = Paths.get(args[0]);
            searchSame(start).forEach(
                    (fileAttr, s) -> System.out.println(fileAttr + ":" + "\n" + s));
        }
    }

    public static Map<SimpleFileVisitor.FileAttr, String> searchSame(Path root) throws IOException {
        SimpleFileVisitor visitor = new SimpleFileVisitor();
        Files.walkFileTree(root, visitor);
        return visitor.getRsl();
    }

    public static class SimpleFileVisitor implements FileVisitor<Path> {

        private Map<FileAttr, String> rsl = new HashMap<>();
        private Map<FileAttr, String> forCheck = new HashMap<>();

        @Override
        public FileVisitResult preVisitDirectory(
                Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(
                Path file, BasicFileAttributes attrs) throws IOException {
            FileAttr fileAttr = new FileAttr(file.getFileName().toString(), attrs.size());
            if (forCheck.containsKey(fileAttr)) {
                if (rsl.containsKey(fileAttr)) {
                    rsl.put(fileAttr, rsl.get(fileAttr)
                            + file.toAbsolutePath().toString() + "\n");
                } else {
                    rsl.put(fileAttr, forCheck.get(fileAttr)
                            + file.toAbsolutePath().toString() + "\n");
                }
            } else {
                forCheck.put(fileAttr, file.toAbsolutePath().toString() + "\n");
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

        public Map<FileAttr, String> getRsl() {
            return rsl;
        }

        private class FileAttr {
            private String name;
            private long size;

            public FileAttr(String name, long size) {
                this.name = name;
                this.size = size;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                FileAttr fileAttr = (FileAttr) o;
                return size == fileAttr.size
                        && Objects.equals(name, fileAttr.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, size);
            }

            @Override
            public String toString() {
                return "name=" + name + "; " + "size=" + size;
            }
        }
    }
}


