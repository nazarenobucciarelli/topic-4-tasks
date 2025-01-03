package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.ios.components.ProductCartComponent;

import java.util.List;

public class CartPage extends AbstractPage {

    @ExtendedFindBy(iosPredicate = "name == \"test-Item\"")
    private List<ProductCartComponent> products;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCartComponent> getProducts() {
        return products;
    }
}
