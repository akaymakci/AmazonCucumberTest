package Pages;

import Utilities.WebDriverUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
public class Parent {
    public void sendKeysFunction(WebElement element, String value){
        waitUntilVisible(element);
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);

    }
    public void waitUntilVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(WebDriverUtility.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) WebDriverUtility.getDriver();
        js.executeScript("arguments[0].scrollIntoView;",element);

    }
    public void clickFunction(WebElement element)
    {
        waitUntilClickable(element);
        scrollToElement(element);
        element.click();
    }
    public void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDriverUtility.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void verifyContainsText(WebElement element, String text)
    {
        waitUntilVisible(element);
        Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }

}
