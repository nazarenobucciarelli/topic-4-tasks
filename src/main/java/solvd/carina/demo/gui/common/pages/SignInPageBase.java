package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SignInPageBase extends AbstractPage {
    public SignInPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isRequiredUsernameErrorDisplayed();

    public abstract boolean isInvalidCredentialsErrorMessageDisplayed();

    public abstract AbstractPage clickLoginButton();

    public abstract void typePassword(String password);

    public abstract void typeUsername(String username);
}
