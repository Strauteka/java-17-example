package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class JavaStreams {

    public static void main(String[] args) {
        Arrays.asList(args).forEach(System.out::println);
        IntStream.range(0, 10)
                .filter(i -> i % 2 == 0)
                .dropWhile(i -> i < 4)
                .limit(3)
                .boxed()
                .sorted((a, b) -> b - a)
                .map(value -> "value of " + value)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        final int reduce = IntStream.range(0, 10).reduce(-45, Integer::sum);
        log.info("Reduce: {}", reduce);

        Stream.concat(IntStream.range(0, 5).boxed(), IntStream.range(3, 7).boxed()).distinct()
                .forEach(i -> log.info("value: {}", i));

        Stream.iterate(0, i -> i + 3)
                .skip(10)
                .limit(3)
                .flatMap(i -> Stream.of(i, i + 1))
                .collect(Collectors.toMap((key) -> "value" + key, (value) -> value))
                .forEach((k, v) -> log.info("{} : {}", k, v));

        final String collect = Stream.generate(UUID::randomUUID)
                .limit(3)
                .sorted()
                .map(UUID::toString)
                .collect(Collectors.joining(","));

        log.info("Collect.joining: {}", collect);
    }


}
