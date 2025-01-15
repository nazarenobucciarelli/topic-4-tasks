package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.models.ProductCart;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.ios.components.ProductCartComponent;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @ExtendedFindBy(iosPredicate = "name == \"test-Item\"")
    private List<ProductCartComponent> products;

    @ExtendedFindBy(iosPredicate = "name == \"test-CHECKOUT\"")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCart> getProducts() {
        return products.stream()
                .map(product -> new ProductCart(product.getTitle(), product.getPrice(), product.getAmount()))
                .collect(Collectors.toList());
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
