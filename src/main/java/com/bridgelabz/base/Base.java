package com.bridgelabz.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public Properties prop;

    public WebDriver init_driver(String browserName) {
        ChromeOptions options = new ChromeOptions();
        if(browserName.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\eclipse-workspace\\CQA_113_SELENIUM_AUTOMATION\\Drivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            if(prop.getProperty("headless").equals("yes")) {
                //headless mode:
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            }else {
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            }
        }
        return driver;
    }

    public Properties init_Properties() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("C:\\Users\\dell\\Documents\\QADevPrograms\\MyKeywordDriven\\src\\main\\java\\com\\bridgelabz\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
