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

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]")
    private ExtendedWebElement cart;

    @ExtendedFindBy(accessibilityId = "test-Menu")
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
        try {
            String cartCount = cart.findExtendedWebElement(By.xpath("//android.widget.TextView"))
                    .getAttribute("text");
            return Integer.parseInt(cartCount);
        } catch (NumberFormatException | NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public LeftSidebarPageBase clickMenuButton() {
        menuButton.click();
        return new LeftSidebarPage(driver);
    }
}
