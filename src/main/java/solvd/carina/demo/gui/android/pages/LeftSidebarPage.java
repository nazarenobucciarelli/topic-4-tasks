package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.pages.LeftSidebarPageBase;
import solvd.carina.demo.gui.common.pages.SignInPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LeftSidebarPageBase.class)
public class LeftSidebarPage extends LeftSidebarPageBase {

    @FindBy(xpath = "//android.widget.TextView[@text=\"LOGOUT\"]")
    private ExtendedWebElement logoutButton;

    public LeftSidebarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SignInPageBase clickLogoutButton() {
        logoutButton.click();
        return new SignInPage(driver);
    }
}
