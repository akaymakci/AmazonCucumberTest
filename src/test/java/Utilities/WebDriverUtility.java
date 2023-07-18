package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.output.NullOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverUtility {

    private static WebDriver driver;
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>(); // WebDriver 1 WebbDriver 2
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>(); // chrome , firefox ...
    public static WebDriverWait wait;
    public static WebDriver getDriver() {

        Logger.getLogger("").setLevel(Level.SEVERE);
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Error");

        if(threadBrowserName.get()==null) { // default driver set to Chrome
            threadBrowserName.set("chrome");
        }

        if (threadDriver.get() == null) { //System runs only one driver

            String browserName = threadBrowserName.get(); // System returns the browsername

            switch (browserName) //choose browser name
            {
                case"chrome":
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
//                    ****************************************************
//                    This lines of code prevents the unnecessary output and provides clean output
                    DriverService.Builder<ChromeDriverService, ChromeDriverService.Builder> serviceBuilder = new ChromeDriverService.Builder();
                    ChromeDriverService chromeDriverService = serviceBuilder.build();
                    chromeDriverService.sendOutputTo(NullOutputStream.NULL_OUTPUT_STREAM);
//                    *****************************************************
                    WebDriverManager.chromedriver().setup();

                    if (!runningFromIntelliJ()) { //if the code run by jenkins or another tools, then runs this driver headless
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1400,2400");
                        threadDriver.set(new ChromeDriver(options));
                    }
                    else
//                        If there is no running driver then starts new Chromedriver
                        threadDriver.set(new ChromeDriver());

                    break;
                case"firefox":
                    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");
                    WebDriverManager.firefoxdriver().setup();
                    threadDriver.set(new FirefoxDriver());  // For this thread Firefox runs, if there is no running another firefoxDriver

                    break;
                case"safari":
                    WebDriverManager.safaridriver().setup();
                    threadDriver.set(new SafariDriver());  // For this thread Safari runs, if there is no running another SafariDriver
                    break;
                case"edge":
                    System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
                    DriverService.Builder<EdgeDriverService, EdgeDriverService.Builder> serviceBuilderEdge = new EdgeDriverService.Builder();
                    EdgeDriverService edgeDriverService = serviceBuilderEdge.build();
                    edgeDriverService.sendOutputTo(NullOutputStream.NULL_OUTPUT_STREAM);
                    WebDriverManager.edgedriver().setup();
                    threadDriver.set(new EdgeDriver());  // For this thread Edge runs, if there is no running another edgeDriver
                    break;
            }
        }
        return threadDriver.get();
    }

    public static void quitDriver(){

     Wait(3);

        if(threadDriver.get() != null){ //if there is running driver
            threadDriver.get().quit();

            WebDriver driver = threadDriver.get();
            driver=null;
            threadDriver.set(driver);
        }
    }

    public static void Wait(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean runningFromIntelliJ(){
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}
