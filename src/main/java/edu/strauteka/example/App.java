package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record App() {
    public static void main(String[] args) {
        log.info("Hello, World!");
    }
}
