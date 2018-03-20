package ru.sbt.jschool.session1;

import java.io.IOException;
import java.io.InputStream;

public class Task3 {
    public static void main(String[] args) throws IOException {
        System.getenv().forEach(Task2.biSout);
//        executeUNIXCommand("printenv");
    }

    // Так тоже можно)
    public static void executeUNIXCommand(String commandName) throws IOException {
        Process process = Runtime.getRuntime().exec(commandName);
        InputStream inputStream = process.getInputStream();
        inputStream.transferTo(System.out);
    }
}
