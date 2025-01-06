package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CheckoutPageBase extends AbstractPage {
    public CheckoutPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void enterFirstName(String firstName);

    public abstract void enterLastName(String lastName);

    public abstract void enterZip(String zip);

    public abstract void clickContinueButton();

    public abstract void clickFinishButton();

    public abstract boolean isThanksForYourOrderTextDisplayed();
}
