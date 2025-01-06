package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.LeftSidebarPageBase;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LeftSidebarPageBase.class)
public class LeftSidebarPage extends LeftSidebarPageBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-LOGOUT\"`]")
    private ExtendedWebElement logoutButton;

    public LeftSidebarPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage clickLogoutButton() {
        logoutButton.click();
        return new SignInPage(driver);
    }
}
