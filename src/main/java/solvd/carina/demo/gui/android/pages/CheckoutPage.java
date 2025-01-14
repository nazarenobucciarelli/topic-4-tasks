package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.pages.CheckoutPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CheckoutPageBase.class)
public class CheckoutPage extends CheckoutPageBase implements IMobileUtils {

    @ExtendedFindBy(accessibilityId = "test-First Name")
    private ExtendedWebElement firstNameInput;

    @ExtendedFindBy(accessibilityId = "test-Last Name")
    private ExtendedWebElement lastNameInput;

    @ExtendedFindBy(accessibilityId = "test-Zip/Postal Code")
    private ExtendedWebElement zipCodeInput;

    @ExtendedFindBy(accessibilityId = "test-CONTINUE")
    private ExtendedWebElement continueButton;

    @ExtendedFindBy(accessibilityId = "test-FINISH")
    private ExtendedWebElement finishButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]")
    private ExtendedWebElement thankYouForYourOrderText;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void enterFirstName(String firstName) {
        firstNameInput.type(firstName);
    }

    @Override
    public void enterLastName(String lastName) {
        lastNameInput.type(lastName);
    }

    @Override
    public void enterZip(String zip) {
        zipCodeInput.type(zip);
    }

    @Override
    public void clickContinueButton() {
        continueButton.click();
    }

    @Override
    public void clickFinishButton() {
        swipe(finishButton);
        finishButton.click();
    }

    @Override
    public boolean isThanksForYourOrderTextDisplayed() {
        return thankYouForYourOrderText.isPresent();
    }

    @Override
    public void checkout(String firstName, String lastName, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZip(zip);
        clickContinueButton();
        clickFinishButton();
    }
}
