package solvd.carina.demo.gui.ios.components;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.ios.pages.CartPage;

public class HeaderComponent extends AbstractUIObject implements IMobileUtils {

    @FindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Cart\"]/parent::XCUIElementTypeOther")
    private ExtendedWebElement cartButton;

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
}
