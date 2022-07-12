package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties properties;
    private static final String propertyFilePath= "C:\\Users\\u729097\\IdeaProjects\\demo\\configs\\Configuration.properties";




    public String getApplicationUrl() throws IOException {
        properties = new Properties();
        FileInputStream ip = new FileInputStream(propertyFilePath);
        properties.load(ip);
        String Url = properties.getProperty("url");
        return Url;
    }

    public String pageurl() throws IOException {
        properties = new Properties();
        FileInputStream ip = new FileInputStream(propertyFilePath);
        properties.load(ip);
        String Url = properties.getProperty("url1");
        return Url;
    }



}
