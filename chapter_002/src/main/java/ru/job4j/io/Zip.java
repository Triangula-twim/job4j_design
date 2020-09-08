package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final List<File> fileList = new ArrayList<>();
    private String cutPath;

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File f: sources) {
                String name = f.getPath()
                        .replace(this.cutPath, "");
                if (f.isFile()) {
                    zip.putNextEntry(new ZipEntry(name));
                    try (BufferedInputStream out = new BufferedInputStream(
                            new FileInputStream(f))) {
                        zip.write(out.readAllBytes());
                    }
                } else {
                    zip.putNextEntry(new ZipEntry(name + "\\"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgZip argZip = new ArgZip(args);
        argZip.valid();
        Zip zip = new Zip();
        List<File> rsl = zip.inDirectory(argZip.directory(), argZip.exclude());
        zip.packFiles(rsl, new File(argZip.output()));
    }

    public List<File> inDirectory(String path, String ext) {
        File file = new File(path);
        File[] children = file.listFiles();
        for (File f: children) {
            if (f.isFile() && !f.getAbsolutePath().endsWith(ext)) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                fileList.add(f);
                inDirectory(f.getAbsolutePath(), ext);
            }
        }
        this.cutPath = path;
        return fileList;
    }
}