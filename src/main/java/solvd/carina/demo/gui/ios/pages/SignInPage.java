package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;

public class SignInPage extends AbstractPage {

    @ExtendedFindBy(iosPredicate = "name == \"test-Username\"")
    private ExtendedWebElement usernameField;

    @ExtendedFindBy(iosPredicate = "name == \"test-Password\"")
    private ExtendedWebElement passwordField;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-LOGIN\"`]")
    private ExtendedWebElement loginButton;

    @ExtendedFindBy(iosPredicate = "name == \"Username and password do not match any user in this service.\"")
    private ExtendedWebElement invalidCredentialsErrorMessage;

    public SignInPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(loginButton);
    }

    public void typeUsername(String username) {
        usernameField.type(username);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public AbstractPage clickLoginButton() {
        loginButton.click();
        if (!loginButton.isVisible()) {
            return new HomePage(driver);
        }
        return new SignInPage(driver);
    }

    public boolean isInvalidCredentialsErrorMessageDisplayed() {
        return invalidCredentialsErrorMessage.isVisible();
    }
}
