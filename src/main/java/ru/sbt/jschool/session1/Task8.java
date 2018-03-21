package ru.sbt.jschool.session1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

interface PropertyHelper {
    String stringValue(String name);
    Integer integerValue(String name);
    Double doubleValue(String name);
}

//    String[] args, аргументы командной строки.</li>
//    String path, путь к property-файлу для использования.</li>
public class Task8 implements PropertyHelper{

    private String[] args;
    private String path;

    public Task8(String[] args) {
        this.args = args;
    }
    public Task8(String path) {
        this.path = path;
    }

    @Override
    public String stringValue(String name) {
        String result = null;

        Path propertyFilePath = Paths.get(path);

        Pattern pattern = Pattern.compile(name+"=(?<VALUE>.*)");
        Optional<Matcher> goodMatcher = Stream.of(args)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .findFirst();

        if (goodMatcher.isPresent())
            result = goodMatcher.get().group("VALUE");
        else if (System.getProperties().containsKey(name))
            result = System.getProperties().getProperty(name);
        else if (System.getenv().containsKey(name))
            result = System.getenv(name);
        else if (Files.exists(propertyFilePath)){
            Properties propertiesFromFile = new Properties();
            try {
                propertiesFromFile.load(Files.newInputStream(propertyFilePath));
                result = propertiesFromFile.getProperty(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    @Override
    public Integer integerValue(String name) {
        return Integer.parseInt(stringValue(name));
    }
    @Override
    public Double doubleValue(String name) {
        return Double.parseDouble(stringValue(name));
    }
}
