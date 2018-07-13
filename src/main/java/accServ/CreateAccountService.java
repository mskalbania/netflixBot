package accServ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static accServ.AccountServiceConstants.*;

public class CreateAccountService {

    private WebDriver webDriver;

    public CreateAccountService() {
        this.webDriver = setUpWebDriver();
    }

    public static Map<String, String> createAccount(final Map<String, String> params) {
        final CreateAccountService accServ = new CreateAccountService();
        return accServ.createAcc(params);
    }

    private WebDriver setUpWebDriver() {
        final ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("headless");
        return new ChromeDriver(chromeOptions);
    }

    private Map<String, String> createAcc(final Map<String, String> params) {
        this.webDriver.get(NETFLIX_URL);
        closeCookieInfoIfPresent();
        clickButton(MAIN_PAGE_BTN);
        clickButton(SEE_PLANS_BTN);
        clickPlan(ULTRA_PLAN);
        waitFor(CONTINUE_BTN);
        clickButton(CONTINUE_BTN);
        clickButton(CONTINUE_BTN);
        fillInElement(EMAIL_FIELD, params.get(EMAIL));
        fillInElement(PASSWORD_FIELD, params.get(PASSWORD));
        clickButton(CONTINUE_BTN);
        clickButton(CREDIT_CARD_BTN);
        fillInElement(CARD_HOLDER_FIELD, params.get(CARD_HOLDER));
        fillInElement(CARD_NUMBER_FIELD, params.get(CARD_NUMBER));
        fillInElement(EXP_DATE_FIELD, params.get(EXP_DATE));
        fillInElement(CVV_FIELD, params.get(CVV));

        this.webDriver.close();
        return Collections.emptyMap();
    }

    private void clickButton(final String buttonName) {
        try {
            final WebElement element = webDriver.findElement(By.cssSelector(buttonName));
            final Actions actions = new Actions(webDriver);
            actions.moveToElement(element).click().build().perform();
            sleep();
        } catch (Exception e) {
            System.out.println("UNABLE TO CLICK: " + buttonName + " RETRYING...");
            clickButton(buttonName);
        }
    }

    private void waitFor(final String element) {
        new WebDriverWait(webDriver, 100)
                .until(d -> d.findElement(By.cssSelector(element)).isDisplayed());
    }

    private void clickPlan(final String planName) {
        final List<WebElement> cells = webDriver.findElements(By.cssSelector(CELL));
        for (final WebElement we : cells) {
            if (we.getText().equals(planName)) {
                we.click();
                break;
            }
        }
        sleep();
    }

    private void closeCookieInfoIfPresent() {
        final List<WebElement> elements = webDriver.findElements(By.cssSelector(CLOSE_COOKIE_BTN));
        if (elements.size() > 0) {
            elements.get(0).click();
        }
    }

    private void fillInElement(final String elementName, final String with) {
        final WebElement webElement = webDriver.findElement(By.cssSelector(elementName));
        webElement.sendKeys(with);
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
