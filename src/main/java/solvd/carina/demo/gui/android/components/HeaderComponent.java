package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.pages.LeftSidebarPage;
import solvd.carina.demo.gui.common.components.HeaderComponentBase;
import solvd.carina.demo.gui.common.pages.LeftSidebarPageBase;
import solvd.carina.demo.gui.android.pages.CartPage;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HeaderComponentBase.class)
public class HeaderComponent extends HeaderComponentBase {

    @ExtendedFindBy(androidUIAutomator = "new UiSelector().description(\"test-Cart\")" +
            ".childSelector(new UiSelector().classNameMatches(\".*Text.*\"))")
    private ExtendedWebElement cart;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]")
    private ExtendedWebElement menuButton;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public CartPage clickCartButton() {
        cart.click();
        return new CartPage(driver);
    }

    @Override
    public Integer getCartCount() {
        if (cart.isElementPresent(3)) {
            try {
                return Integer.parseInt(cart.getText().trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing cart item count: " + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public LeftSidebarPageBase clickMenuButton() {
        menuButton.click();
        return new LeftSidebarPage(driver);
    }
}
