package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(){
        properties = new Properties();
        try{
            FileInputStream inputStream = new FileInputStream("src//test//resources//config.properties");
            properties.load(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public String getUsername(){
        return properties.getProperty("username");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getBrowser(){
        return properties.getProperty("browser");
    }

    public String getUrl(){
        return properties.getProperty("url");
    }
}
