package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

@Slf4j
public class JavaFunction {
    public static void main(String[] args) {
        //Look in java.util.function for all ready to go functional interface

        // Predicate
        new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 10 == 0;
            }
        };

        final Predicate<Integer> predicate = (Integer i) -> i % 10 == 0;

        //Bi predicate

        new BiPredicate<Integer, Integer>() {
            @Override
            public boolean test(Integer integer, Integer integer2) {
                return (integer % integer2) == 0;
            }
        };

        final BiPredicate<Integer, Integer> integerComparator = (Integer a, Integer b) -> a % b == 0;

        // Consumer
        new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {

            }
        };

        final Consumer<Integer> consumer = i -> log.info("Consumer: {}", i);

        // Bi consumer
        final BiConsumer<Integer, String> integerStringBiConsumer = new BiConsumer<>() {
            @Override
            public void accept(Integer integer, String s) {

            }
        };

        final BiConsumer<Integer, String> biConsumer = (Integer a, String b) -> log.info("BiConsumer: {} : {}", a, b);

        // Function,
        new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "Value of " + integer;
            }
        };

        final Function<Integer, String> integerStringFunction = (Integer i) -> "Value of " + i;
        final Function<Integer, Integer> next = integerStringFunction.andThen(String::length);
        log.info("Function: {}", next.apply(3));

        //BiFunction
        new BiFunction<Integer, Integer, Double>() {
            @Override
            public Double apply(Integer o1, Integer o2) {
                return Double.valueOf(o1) / o2;
            }
        };

        final BiFunction<Integer, Integer, Double> biFunction = (Integer a, Integer b) -> Double.valueOf(a) / b;
        log.info("BiFunction: {}", biFunction.apply(1, 3));

        // Supplier
        new Supplier<Integer>() {
            @Override
            public Integer get() {
                return ThreadLocalRandom.current().nextInt(10);
            }
        };

        final Supplier<Integer> supplier = () -> ThreadLocalRandom.current().nextInt(10);
        log.info("Supplier: {}", supplier.get());
    }
}
