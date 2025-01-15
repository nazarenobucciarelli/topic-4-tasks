package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.models.ProductCart;

import java.util.List;

public abstract class CartPageBase extends AbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract List<ProductCart> getProducts();

    public abstract CheckoutPageBase clickCheckoutButton();
}
