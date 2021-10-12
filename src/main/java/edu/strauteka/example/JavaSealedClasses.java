package edu.strauteka.example;

public class JavaSealedClasses {
    public static void main(String[] args) {

    }

    abstract static sealed class Shape permits Circle {

    }

    static final class Circle extends Shape {

    }

//    public static final class Tree extends Shape {
//
//    }
}
