package ru.sbt.jschool.session1;

import java.io.IOException;
import java.util.function.BiConsumer;

public class Task2 {
    public static BiConsumer<Object, Object> biSout = (k, v)->System.out.printf("%-40s\t%s%n",k,v);

    public static void main(String[] args) throws IOException {
        System.getProperties().forEach(biSout);
    }
}
