package ru.sbt.jschool.session1;

import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) {
        Stream.of(args).forEach(System.out::println);
    }
}
