package StepDefinitions;

import Utilities.WebDriverUtility;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Hooks {
    @Before
    public void before(){
        System.out.println();
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("Scenario starts");
        System.out.println("*******************************************************");
        System.out.println();
        System.out.println();
    }
    @After
    public void after(Scenario scenario){
        System.out.println();
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("Scenario ended");
        System.out.println("Scenario result="+ scenario.getStatus());
        System.out.println("Scenario isFailed ?="+ scenario.isFailed());
        System.out.println("*******************************************************");
        System.out.println();
        System.out.println();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

        if (scenario.isFailed()){ // Take screenshot if the scenario fails
            // to the File
            TakesScreenshot screenshot = (TakesScreenshot) WebDriverUtility.getDriver();
            File screenShotFile = screenshot.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShotFile,
                        new File("target/FailedScreenShots/"+ scenario.getId()+date.format(formatter)+".png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WebDriverUtility.quitDriver();

    }
    public String getBase64Screenshot()
    {
        return ((TakesScreenshot) WebDriverUtility.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

}
