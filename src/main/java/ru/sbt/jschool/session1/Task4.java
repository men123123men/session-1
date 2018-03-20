package ru.sbt.jschool.session1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("JSCHOOl1_COUNT=(?<COUNT>\\d+)");
        Matcher matcher = pattern.matcher(args[0]);

        if (matcher.matches()) {
            printHello(matcher.group("COUNT"));
            return;
        }
        if (System.getProperties().containsKey("JSCHOOl1_COUNT")){
            printHello(System.getProperty("JSCHOOl1_COUNT"));
            return;
        }
        if (System.getenv().containsKey("JSCHOOl1_COUNT")){
            printHello(System.getenv("JSCHOOl1_COUNT"));
            return;
        }
        if (System.getenv().containsKey("JSCHOOL1_PROPERTIES_FILE")) {
            String fileName = System.getenv("JSCHOOL1_PROPERTIES_FILE");
            printHello(getCountFromFile(fileName));
            return;
        }
        System.out.println("сообщение о вариантах исопльзования программы))");
    }
    public static void printHello(String countOfHellowStr){
        int countOfHellowInt = Integer.parseInt(countOfHellowStr);
        for (int i = 0; i< countOfHellowInt;i++)
            System.out.println("Hello, World!");
    }
    public static String getCountFromFile (String fileName) {
        String[] fileLines = null;
        try {
            fileLines =  Files.lines(Paths.get(fileName)).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Плохой файл!!!!");
        }
        return fileLines[0];
    }
}
