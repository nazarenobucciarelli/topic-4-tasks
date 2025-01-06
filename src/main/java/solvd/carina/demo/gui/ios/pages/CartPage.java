package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.ios.components.ProductCartComponent;

import java.util.List;

public class CartPage extends CartPageBase {

    @ExtendedFindBy(iosPredicate = "name == \"test-Item\"")
    private List<ProductCartComponent> products;

    @ExtendedFindBy(iosPredicate = "name == \"test-CHECKOUT\"")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCartComponent> getProducts() {
        return products;
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
