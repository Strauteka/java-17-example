package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JavaFile {
    //Windows
    //mvn clean compile exec:java -D"exec.mainClass"="edu.strauteka.example.JavaFile" -D"exec.args"="C:\Repositories\java\example"
    public static void main(String[] args) {
        try {
            log.info("File: {}", loadFromFile());
            loadFromFile();
        } catch (IOException e) {
            log.error("Error: ", e);
        }

        final File file = Optional.ofNullable(args)
                .stream()
                .filter(arg -> arg.length != 0)
                .map(arg -> arg[0])
                .map(File::new)
                .findAny().orElse(new File("C:\\Repositories\\java\\example"));
        listFilesForFolder(file);
    }

    public static String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/test.txt")));
    }

    public static void listFilesForFolder(final File folder) {
        final File[] files = folder.listFiles();
        if(Objects.nonNull(files)) {
            for (final File fileEntry : files) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    log.info("File name: {}", fileEntry.getName());
                }
            }
        } else {
            log.info("File not found!");
        }
    }
}
