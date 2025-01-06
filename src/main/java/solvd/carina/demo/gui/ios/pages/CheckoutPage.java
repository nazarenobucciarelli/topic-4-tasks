package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.CheckoutPageBase;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutPageBase.class)
public class CheckoutPage extends CheckoutPageBase {

    @ExtendedFindBy(iosPredicate = "name == \"test-First Name\"")
    private ExtendedWebElement firstNameInput;

    @ExtendedFindBy(iosPredicate = "name == \"test-Last Name\"")
    private ExtendedWebElement lastNameInput;

    @ExtendedFindBy(iosPredicate = "name == \"test-Zip/Postal Code\"")
    private ExtendedWebElement zipInput;

    @ExtendedFindBy(iosPredicate = "name == \"test-CONTINUE\"")
    private ExtendedWebElement continueButton;

    @ExtendedFindBy(iosPredicate = "name == \"test-FINISH\"")
    private ExtendedWebElement finishButton;

    @ExtendedFindBy(iosPredicate = "name == \"THANK YOU FOR YOU ORDER\"")
    private ExtendedWebElement thanksForYouOrderText;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.type(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.type(lastName);
    }

    public void enterZip(String zip) {
        zipInput.type(zip);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public boolean isThanksForYourOrderTextDisplayed() {
        return thanksForYouOrderText.isVisible();
    }
}
