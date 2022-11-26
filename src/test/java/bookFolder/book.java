package bookFolder;

import bookFolder.bookAttr;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class book {
    WebDriver driver;

    @Given("User go to {string}")
    public void userGoToHttpsDemoqaComBooks(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("User in {string} page")
    public void userInBookStorePage(String pageTitle) {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demoqa.com/books" );

        WebElement mainPageTitle = driver.findElement(By.className("main-header"));
        Assert.assertEquals(true, mainPageTitle.isDisplayed());
        Assert.assertEquals(pageTitle, mainPageTitle.getText() );
    }

    @And("User search book {string}")
    public void userSearchBookQaEngineer(String bookName) {
        WebElement inputBookName = driver.findElement(By.id("searchBox"));
        bookAttr thisBook = new bookAttr();
        thisBook.setBookNameString(bookName);
        inputBookName.sendKeys(thisBook.getBookName());
    }

    @Then("User see {string}")
    public void userSeeNoRowsFound(String warningMessage) {
        WebElement noRowsErrorMessage = driver.findElement(By.className("rt-noData"));
        Assert.assertEquals(true, noRowsErrorMessage.isDisplayed());
        Assert.assertEquals(warningMessage, noRowsErrorMessage.getText());
    }

    @And("User click {string}")
    public void userClickBookGitPocketGuide(String bookName) {
        bookAttr thisBook = new bookAttr();
        thisBook.setBookNameString(bookName);
        WebElement bookLink = driver.findElement(By.linkText(thisBook.getBookName()));
        bookLink.click();
    }

    @Then("User see {string} book")
    public void userSeeGitPocketGuide(String bookName) {
        bookAttr thisBook = new bookAttr();
        thisBook.setBookNameString(bookName);
        WebElement bookTitleName = driver.findElement(By.xpath("//*[@id=\"userName-value\"][contains (text(), '"+thisBook.getBookName()+"')]"));
        Assert.assertEquals(thisBook.getBookName(), bookTitleName.getText());
    }

}

