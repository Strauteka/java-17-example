package edu.strauteka.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JavaListForEachConsumer {
    public static void main(String[] args) {

        final List<Integer> integers = new ArrayList<Integer>();
        for(int i=0; i<10; i++) integers.add(i);


        integers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.printf("i:%d%n", integer);
            }
        });

        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).forEach(new MyConsumer());

        integers.forEach(integer -> System.out.printf("lambda :%d%n", integer));
    }

    static class MyConsumer implements Consumer<Integer> {
        public void accept(Integer integer) {
            System.out.printf("Custom :%d%n", integer);
        }
    }
}
