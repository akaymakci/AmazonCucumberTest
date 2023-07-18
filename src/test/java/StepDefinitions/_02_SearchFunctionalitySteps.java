package StepDefinitions;

import Pages.DialogContent;
import Utilities.WebDriverUtility;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

public class _02_SearchFunctionalitySteps {
    DialogContent dc = new DialogContent();

    @And("search for Product")
    public void searchForProduct(DataTable elements) {
        List<List<String>> listElement = elements.asLists(String.class);
        for (int i = 0; i < listElement.size(); i++) {
            dc.findAndSend(listElement.get(i).get(0), listElement.get(i).get(1));
        }

    }

    @When("navigate to page number requested page")
    public void navigateToPageNumberRequestedPage(DataTable elements) {
        List<List<String>> listElement = elements.asLists(String.class);
        for (int i = 0; i < listElement.size(); i++) {
            for (int j = 0; j < Integer.parseInt(listElement.get(i).get(1))-1; j++)
                dc.findAndClick(listElement.get(i).get(0));
//            dc.waitUntilVisible(dc.getCurrentPage());
//            System.out.println("page number = "+dc.getCurrentPage().getText());
        }

    }
    public static int num = 0;
    @And("select item on the requested row")
    public void selectItem(DataTable elements) {
        List<List<String>> listElement = elements.asLists(String.class);
        for (int i = 0; i < listElement.size(); i++) {
               num = Integer.parseInt(listElement.get(i).get(1));
                dc.findAndClick(listElement.get(i).get(0));
            }
    }
    public static int getNum(){
        return num-1;
    }

    @Then("the user is able to add it to the cart")
    public void theUserIsAbleToAddItToTheCart(DataTable elements) {
        List<String>  listElement = elements.asList(String.class);
        for (String s : listElement) {
            dc.findAndClick(s);
        }

    }

    @Then("Success message should be displayed")
    public void successMessageShouldBeDisplayed() {
        dc.findAndContainsText("successMessage", "hinzugefügt");
    }
}
