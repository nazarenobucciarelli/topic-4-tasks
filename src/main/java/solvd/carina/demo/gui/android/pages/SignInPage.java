package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.pages.SignInPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SignInPageBase.class)
public class SignInPage extends SignInPageBase {

    @ExtendedFindBy(accessibilityId = "test-Username")
    private ExtendedWebElement usernameField;

    @ExtendedFindBy(accessibilityId = "test-Password")
    private ExtendedWebElement passwordField;

    @ExtendedFindBy(accessibilityId = "test-LOGIN")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Username is required\"]")
    private ExtendedWebElement usernameErrorMessage;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
    private ExtendedWebElement invalidCredentialsMessage;

    public SignInPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(loginButton);
    }

    @Override
    public boolean isRequiredUsernameErrorDisplayed() {
        return usernameErrorMessage.isPresent();
    }

    @Override
    public boolean isInvalidCredentialsErrorMessageDisplayed() {
        return invalidCredentialsMessage.isVisible();
    }

    @Override
    public AbstractPage clickLoginButton() {
        loginButton.click();
        if (loginButton.isPresent()) {
            return new SignInPage(driver);
        }
        return new HomePage(driver);
    }

    @Override
    public void typePassword(String password) {
        passwordField.type(password);
    }

    @Override
    public void typeUsername(String username) {
        usernameField.type(username);
    }
}
