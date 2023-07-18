package Pages;

import StepDefinitions._02_SearchFunctionalitySteps;
import Utilities.WebDriverUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;
import java.util.List;

public class DialogContent extends Parent{

    public DialogContent() {
        PageFactory.initElements(WebDriverUtility.getDriver(), this);
    }

//    @FindBy(xpath = "//form//div//span//span[@class='a-button-text']")
    @FindBy(css = "input[id=\"sp-cc-accept\"]")
    private WebElement getAcceptCookies;
    @FindBy(xpath = "//input[@type='email']")
    private WebElement userName;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passWord;
    @FindBy(className = "mat-button-wrapper")
    private  WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(),'Mein Konto')]")
    private WebElement meinKonto;
    @FindBy(xpath = "//span[contains(text(), 'Konto und Listen')]")
    private WebElement kontoUndListen;
    @FindBy(xpath = "//span[contains(text(), 'Weiter')]")
    private WebElement weiter;
    @FindBy(id = "signInSubmit")
    private WebElement anmelden;
    @FindBy(xpath = "//span[contains(text(),'Benutzer')]")
    private WebElement halloBenutzer;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchBar;
    public WebElement getNextPage() {
        return nextPage;
    }
    @FindBy(xpath = "//*[text()='Weiter']")
    private WebElement nextPage;
    public WebElement getCurrentPage() {
        return currentPage;
    }
    @FindBy(xpath = "//span[contains(@class,'selected')]")
    private WebElement currentPage;
    @FindBy(xpath = "//div[@class='sg-col-inner']//div[contains(@class,'a-spacing-top-small')]//div//h2//a[contains(@class,'a-text-normal')]")
    private List<WebElement> itemNumber;
    @FindBy(id="add-to-cart-button")
    private WebElement addToCart;
    @FindBy(xpath = "//span[contains(text(),'hinzugefügt')]")
    private WebElement successMessage;

    WebElement myElement;

    public void findAndSend(String strElement, String value){

        switch (strElement)
        {
            case "userName" : myElement = userName; break;
            case "passWord" : myElement = passWord; break;
            case "searchBar" : myElement = searchBar; break;
        }
        sendKeysFunction(myElement, value);
    }
    public void findAndClick(String strElement){

        switch (strElement)
        {
            case "getAcceptCookies": myElement = getAcceptCookies; break;
            case "loginButton" : myElement = loginButton; break;
            case "meinKonto" : myElement = meinKonto; break;
            case "kontoUndListen": myElement = kontoUndListen; break;
            case "weiter": myElement = weiter; break;
            case "anmelden": myElement = anmelden; break;
            case "nextPage": myElement = nextPage; break;
            case "itemNumber":
                List<WebElement> itemlist = new ArrayList<>(itemNumber);
                int number = _02_SearchFunctionalitySteps.getNum();
                myElement = itemlist.get(number); break;
            case "addToCart": myElement = addToCart; break;

        }
        clickFunction(myElement);
    }
    public void findAndContainsText(String strElement, String text) {

        switch (strElement) {
            case "halloBenutzer": myElement = halloBenutzer; break;
            case "successMessage": myElement = successMessage; break;
        }
        verifyContainsText(myElement, text);
    }


}
