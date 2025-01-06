package solvd.carina.demo.gui.ios.components;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.components.HeaderComponentBase;
import solvd.carina.demo.gui.ios.pages.CartPage;
import solvd.carina.demo.gui.ios.pages.LeftSidebarPage;

public class HeaderComponent extends HeaderComponentBase implements IMobileUtils {

    @FindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Cart\"]/parent::XCUIElementTypeOther")
    private ExtendedWebElement cartButton;

    @ExtendedFindBy(iosPredicate = "name == \"test-Menu\"")
    private ExtendedWebElement menuButton;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CartPage clickCartButton() {
        tap(cartButton.getLocation().getX() + 40, cartButton.getLocation().getY() + 30);
        return new CartPage(driver);
    }

    public Integer getCartCount() {
        return Integer.parseInt(cartButton.getAttribute("label"));
    }

    public LeftSidebarPage clickMenuButton() {
        tap(menuButton.getLocation().getX() + 10, menuButton.getLocation().getY() + 35);
        return new LeftSidebarPage(driver);
    }
}
