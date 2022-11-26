package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class select_menus {
    WebDriver driver;
    @Given("User go to {string}")
    public void userGoToHttpsDemoqaComSelectMenu(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("User in “select menu” page")
    public void userInSelectMenuPage() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demoqa.com/select-menu" );

        WebElement mainPageTitle = driver.findElement(By.className("main-header"));
        Assert.assertEquals(true, mainPageTitle.isDisplayed());
    }

    @And("User choose select value {string}")
    public void userChooseSelectValueAnotherRootOption(String textDropdown) {
        WebElement selectValueDropdown = driver.findElement(By.xpath("//*[@id=\"withOptGroup\"]/div/div[1]"));
        selectValueDropdown.click();
        WebElement selectValueDropdownBox = driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectValueDropdownBox);
        driver.findElement(By.xpath("//*[text()='" + textDropdown + "']")).click();
    }

    @And("User choose select one {string}")
    public void userChooseSelectOneOther(String textDropdown) {
        WebElement selectOneDropdown = driver.findElement(By.xpath("//*[@id=\"selectOne\"]/div/div[1]"));
        selectOneDropdown.click();
        WebElement selectOneDropdownBox = driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[4]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectOneDropdownBox);
        driver.findElement(By.xpath("//*[text()='" + textDropdown + "']")).click();
    }

    @And("User choose old style select menu {string}")
    public void userChooseOldStyleSelectMenuAqua(String textDropdown) {
        WebElement selectOldStyleMenuDropdown = driver.findElement(By.xpath("//*[@id=\"oldSelectMenu\"]"));
        Select oldStyleMenuDropdown = new Select(selectOldStyleMenuDropdown);
        oldStyleMenuDropdown.selectByVisibleText(textDropdown);
    }

    @And("User choose multi select drop down all color")
    public void userChooseMultiSelectDropDownAllColor() {
        WebElement selectMultiDropdown = driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div/div/div[2]"));
        selectMultiDropdown.click();
        WebElement selectOneDropdownBox = driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectOneDropdownBox);
//        driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7][contains (text(), 'Green')]")).click();
    }

    @Then("User success input all select menu")
    public void userSuccessInputAllSelectMenu() {
        String expectedSelectValue = "Another root option";
        WebElement selectValueDropdown = driver.findElement(By.xpath("//*[@id=\"withOptGroup\"]/div/div[1]"));
        Assert.assertEquals(expectedSelectValue, selectValueDropdown.getText());

        String expectedSelectOne = "Other";
        WebElement selectOneDropdown = driver.findElement(By.xpath("//*[@id=\"selectOne\"]/div/div[1]"));
        Assert.assertEquals(expectedSelectOne, selectOneDropdown.getText());

        String expectedOldStyle = "Aqua";
        WebElement selectOldStyleMenuDropdown = driver.findElement(By.xpath("//*[@id=\"oldSelectMenu\"]"));
        Select select = new Select(selectOldStyleMenuDropdown);
        String option = select.getFirstSelectedOption().getText();
        Assert.assertEquals(expectedOldStyle,option);
    }
}
