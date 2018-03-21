package ru.sbt.jschool.session1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Task4 {
    public static void main(String[] args) throws IOException {

        String count;
        String countPropertyName = "JSCHOOl1_COUNT";
        String propertyFileNameInEnvironment = "JSCHOOL1_PROPERTIES_FILE";

        Pattern pattern = Pattern.compile(countPropertyName+"=(?<COUNT>\\d+)");
        Optional<Matcher> goodMatcher = Stream.of(args)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .findFirst();

        if(goodMatcher.isPresent())
            count = goodMatcher.get().group("COUNT");

        else if (System.getProperties().containsKey(countPropertyName))
            count = System.getProperty(countPropertyName);

        else if (System.getenv().containsKey(countPropertyName))
            count = System.getenv(countPropertyName);

        else if (System.getenv().containsKey(propertyFileNameInEnvironment)){
            String fileName = System.getenv(propertyFileNameInEnvironment);
            count = getValueFromPropertyFile(fileName, countPropertyName);

        } else {
            System.out.println("ничего не подошло, короче...");
            return;
        }
        printHello(count);
    }
    public static void printHello(String countOfHellowStr){
        int countOfHellowInt = Integer.parseInt(countOfHellowStr);
        for (int i = 0; i< countOfHellowInt;i++)
            System.out.println("Hello, World!");
    }
    public static String getValueFromPropertyFile (String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(fileName)));
        return properties.getProperty(key);
    }
}
