package com.bridgelabz.keywordengine;

import com.bridgelabz.base.Base;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Keyword_Engine extends Base {
    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    public final String SCENARIO_SHEET_PATH = "C:\\Users\\dell\\Documents\\QADevPrograms\\MyKeywordDriven\\src\\main\\resources\\fb_Scenarios.xlsx";

    public void start_Execution(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        int k=0;
        for(int i=0; i<sheet.getLastRowNum(); i++) {
            try {
                String locatorType = sheet.getRow(i+1).getCell(k+1).toString().trim();
                String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();
                String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
                String value = sheet.getRow(i+1).getCell(k+4).toString().trim();

                switch (action) {
                    case "open browser":
                        base = new Base();
                        prop = base.init_Properties();
                        if(value.isEmpty() || value.equals("NA")) {
                            driver = base.init_driver(prop.getProperty("browser"));
                        }else{
                            driver = base.init_driver(value);
                        }
                        break;

                    case "enter url":
                        if(value.isEmpty() || value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        }else {
                            driver.get(value);
                        }
                        break;

                    case "quit":
                        driver.quit();
                        break;
                    default:
                        break;
                }

                switch (locatorType) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if(action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if(action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        }
                        locatorType=null;
                        break;

                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if(action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if(action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if(action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                            System.out.println("Home Page is verified - User is able to sign in successfully");
                        } else {
                            System.out.println("User is not able to Sign In - Invalid Credential");
                        }
                        locatorType=null;
                        break;

                    default:
                        break;
                }
            }
            catch(Exception e) {
            }
        }
    }
}
