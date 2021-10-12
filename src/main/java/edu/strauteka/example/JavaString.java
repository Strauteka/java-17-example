package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
public class JavaString {
    public static void main(String[] args) {
        String newLine = System.getProperty("line.separator");
        final String s = "  ".concat(newLine)
                .concat("Some")
                .concat(newLine)
                .concat("long")
                .concat(newLine)
                .concat("string  ");

        Arrays.stream(s.strip().split(newLine,2)).forEach(value  -> log.info("{}", value));

        log.info("value is blank: {}", "   ".isBlank());

        try {
            log.info("File: {}", loadFromFile());
            loadFromFile();
        } catch (IOException e) {
            log.error("Error: ", e);
        }

       // listFilesForFolder(new File("/home/you/Desktop"));
    }

    public static String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/test.txt")));
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

}
