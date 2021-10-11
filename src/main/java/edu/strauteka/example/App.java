package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Slf4j
public record App(String[] args, ScheduledExecutorService executorService) {

    public static void main(String[] args) {
        new App(args, Executors.newSingleThreadScheduledExecutor()).selfScheduledRun(0);
    }

    public static String who(Object obj) {
        return switch (obj) {
            case Integer
                    i -> "It is an integer: " + i;
            case Long
                    s -> "It is a Long: " + s;
            case String
                    s -> "It is a string: " + s;
            case Boolean
                    s -> "It is a boolean: " + s;
            case null -> "It is a Null";
            default -> "It is none of the known data types";
        };
    }

    public static Object get(Integer value) {
        final Object localValue = value > 15 ? null : value;
        Object x = switch (localValue) {
            case Integer val && val < 3: yield ThreadLocalRandom.current().nextBoolean();
            case Integer val && val < 6: yield "Some String value:" + val;
            case Integer val && val < 9: yield val;
            case Integer val && val < 12: yield Long.valueOf(val);
            case null: yield null;
            default: yield "Not handled case!";
        };
        log.info("Found value: {}", x);
        return x;
    }

    public void selfScheduledRun(Integer inSeconds) {
        log.info("{}", String.join(",", args));
        executorService.schedule(
                () -> {
                    log.info("Finding Value {}", who(get(ThreadLocalRandom.current().nextInt(20))));
                    selfScheduledRun(5);
                },
                inSeconds,
                TimeUnit.SECONDS
        );
    }
}
