package edu.strauteka.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JavaGenericAndLambda {

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("R1");
            }
        };

        Runnable r2 = () -> System.out.println("R2");

        Compare<Integer> c = (Integer o1, Integer o2) -> (o1 - o2) > 0;

        try {
            new Bigger<>(5, 5);
        } catch (IllegalArgumentException e) {
            log.error("Error:", e);
        }

        try {
            new Bigger<>(null, 5);
        } catch (NullPointerException e) {
            log.error("Error:", e);
        }

        final Boolean biggerInteger = new Bigger<Integer>(5, 8).isFirstBigger((a, b) -> (a - b) > 0);
        final Boolean biggerString = new Bigger<String>("Some String", "Other String")
                .isFirstBigger((a, b) -> (a.length() - b.length()) > 0);
        System.out.printf("%b%n%b%n%d%n", biggerInteger, biggerString, Bigger.count);
    }

    record Bigger<T>(T a, T b) {
        private static int count;
        Bigger {
            if (Objects.equals(a, b)) throw new IllegalArgumentException("Equal items not supported!");
            Objects.requireNonNull(a, "Values cant be null");
            Objects.requireNonNull(b, "Values cant be null");
            count++;
        }

        Boolean isFirstBigger(Compare<T> c) {
            return c.compare(this.a, this.b);
        }
    }

    interface Compare<T> {
        boolean compare(T o1, T o2);
    }
}
