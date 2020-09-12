package PageObjects;

import utilis.Utilis;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilis.PageTitles;

public class HomePage {

    WebDriver driver;
    Utilis utilis;
    static Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilis = new Utilis(driver);
    }

    @FindBy(linkText = "Logout")
    WebElement logout;

    @FindBy(xpath = "//a[contains(text(),'Change Password')]")
    WebElement changePassword;


    public void clickOnLogout() {
        log.info("Click on logout");
        utilis.ClickOnButton(logout);
        String pageTitle = driver.getTitle();
        log.info("Page title:" + pageTitle);
        Assert.assertEquals(pageTitle, PageTitles.LOGOUT.title);
        log.info("assertion equals success");
    }

    public void clickOnChangePassword() {
        log.info("Click on Changepassword");
        utilis.ClickOnButton(changePassword);

    }

}
