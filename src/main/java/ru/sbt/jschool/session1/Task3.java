package ru.sbt.jschool.session1;

import java.util.function.BiConsumer;

public class Task3 {
    public static void main(String[] args) {
        BiConsumer<String, String> test = (k,v)->System.out.printf("%s/t%s%n",k,v);
        System.getenv().forEach(test);
    }
}
