package ru.sbt.jschool.session1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static void main(String[] args) throws IOException {
        //(?<city>[A-Za-z\\s]+)
        Pattern pattern = Pattern.compile("JSCHOOl1_COUNT=(?<COUNT>/d+)");
        Matcher matcher = pattern.matcher(args[0]);

        if (matcher.find()) {
            printHello(matcher.group("COUNT"));
            System.exit(0);
        }
        if (System.getProperties().containsKey("JSCHOOl1_COUNT")){
            printHello(System.getProperty("JSCHOOl1_COUNT"));
            System.exit(0);
        }
        if (System.getenv().containsKey("JSCHOOl1_COUNT")){
            printHello(System.getenv().get("JSCHOOl1_COUNT"));
             System.exit(0);
        }
//        if (System.getenv().containsKey("JSCHOOL1_PROPERTIES_FILE"))
//            printHello(getCountFromFile(System.getenv().get("JSCHOOL1_PROPERTIES_FILE")));

    }
    public static void printHello(String countOfHellowStr){
        int countOfHellowInt = Integer.parseInt(countOfHellowStr);
        for (int i = 0; i< countOfHellowInt;i++)
            System.out.println("Hello, World!");
    }
    public static String getCountFromFile (String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).toArray(String[]::new)[0];
    }


}
