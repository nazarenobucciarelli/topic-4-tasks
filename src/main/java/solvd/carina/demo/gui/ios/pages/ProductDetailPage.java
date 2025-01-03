package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends AbstractPage {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Description\"`]/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Description\"`]/XCUIElementTypeStaticText[2]")
    private ExtendedWebElement description;

    @ExtendedFindBy(iosPredicate = "name == \"test-Price\"")
    private ExtendedWebElement price;

    @ExtendedFindBy(iosPredicate = "name == \"ADD TO CART\"")
    private ExtendedWebElement addToCartButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean areElementsPresent() {
        return title.isPresent() && description.isPresent() && price.isPresent() && addToCartButton.isPresent();
    }
}
