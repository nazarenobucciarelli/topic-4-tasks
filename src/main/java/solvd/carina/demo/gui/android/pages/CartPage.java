package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.android.components.ProductCartComponent;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.common.pages.CheckoutPageBase;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @ExtendedFindBy(accessibilityId = "test-Item")
    private List<ProductCartComponent> products;

    @ExtendedFindBy(accessibilityId = "test-CHECKOUT")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<? extends ProductCartComponentBase> getProducts() {
        return products;
    }

    @Override
    public CheckoutPageBase clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
