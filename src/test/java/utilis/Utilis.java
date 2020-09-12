package utilis;

import org.apache.log4j.Logger;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utilis {

    WebDriver driver;
    static Logger log = Logger.getLogger(Utilis.class);
    public WebDriverWait wait;

    public Utilis(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void ClickOnButton(WebElement element) {
        waitVisibility(element);
        if (element.isEnabled()) {
            log.info(element + " : is enabled");
            element.click();
            log.info("Clicked on element");
        }
    }

    private void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sendKeys(WebElement element, String input) {
        waitVisibility(element);
        if (element.isDisplayed()) {
            log.info(element + " : is displayed and values for the element: " + input);
            element.sendKeys(input);
            log.info("value sent to element");

        }

    }

    public static String jsonReader(String key) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("data.json");
        Object obj = JSONValue.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;
        String value = (String) jsonObject.get(key);
        return value;
    }


}


