package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

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

        Arrays.stream(s.strip().split(newLine,2))
                .map(val -> val.replace(newLine, " "))
                .forEach(value  -> log.info("{}", value));

        log.info("value is blank: {}", "   ".isBlank());
    }
}
