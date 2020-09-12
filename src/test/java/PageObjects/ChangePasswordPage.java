package PageObjects;

import Tests.Tests;
import utilis.Utilis;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChangePasswordPage {

    WebDriver driver;
    private HomePage homePage;
    Utilis utilis;
    static Logger log = Logger.getLogger(Tests.class);


    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilis = new Utilis(driver);
        homePage = new HomePage(driver);
    }

    @FindBy(id = "current_pass")
    WebElement currentPassword;

    @FindBy(id = "new_password")
    WebElement newPassword;

    @FindBy(id = "re_password")
    WebElement confirmPassword;

    @FindBy(how = How.ID, using = "Submit")
    WebElement submitButton;

    @FindBy(xpath = "//span[contains(text(),'Your Password is successfully updated!!!')]")
    WebElement passwordChanged;

    public void ChangePassword(String currentPass, String newPass, String confirmPass) {
        homePage = new HomePage(driver);
        homePage.clickOnChangePassword();
        currentPassword.sendKeys(currentPass);
        newPassword.sendKeys(newPass);
        confirmPassword.sendKeys(confirmPass);
        utilis.ClickOnButton(submitButton);
        Assert.assertTrue(passwordChanged.isDisplayed());
        log.info("assertion true success");

    }
}
