package Tests;

import PageObjects.ChangePasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import utilis.Utilis;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;


public class Tests extends browser {


    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;
    HomePage homePage;
    static String username;
    static String pass;
    static Logger log = Logger.getLogger(Tests.class);

    @BeforeMethod
    public void setup(Method method) {
        this.loginPage = new LoginPage(driver);
        this.changePasswordPage = new ChangePasswordPage(driver);
        this.homePage = new HomePage(driver);
    }

    @Test
    public void TestLogin(Method method) throws IOException, ParseException {
        test = extent.createTest(method.getName());
        log.info("Test case started");
        username = Utilis.jsonReader("username");
        pass = Utilis.jsonReader("password");
        log.info("Login using " + username + "," + pass);
        loginPage.login(username, pass);
        homePage.clickOnLogout();
        log.info("Test case passed");
    }

    @Test
    public void ChangePassword(Method method) {
        test = extent.createTest(method.getName());
        log.info("Login using " + username + "," + pass);
        loginPage.login(username, pass);
        log.info("Change password from " + pass + " to " + pass.concat("2"));
        changePasswordPage.ChangePassword(pass, pass.concat("2"), pass.concat("2"));
        log.info("Test case passed");

    }

    @Test(dependsOnMethods = "ChangePassword")
    public void VerifyLoginOnPasswordChange(Method method) {
        test = extent.createTest(method.getName());
        loginPage.login(username, pass.concat("2"));
        loginPage.login(username, pass.concat("2"));
        homePage.clickOnLogout();
        log.info("Test case passed");
    }


}
