package PageObjects;

import Tests.Tests;
import utilis.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LoginPage {

    WebDriver driver;
    Utilis utilis;
    static Logger log = Logger.getLogger(Tests.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilis = new Utilis(driver);
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButton;


    public void login(String name, String pass) {
        utilis.sendKeys(username, name);
        utilis.sendKeys(password, pass);
        utilis.ClickOnButton(loginButton);
        String pageTitle = driver.getTitle();
        log.info("Page title is " + pageTitle);
        Assert.assertEquals(pageTitle, PageTitles.HOMEPAGE.title);
        log.info("assertion equals success");
    }
}
