package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.ProductDetailPageBase;

public class ProductDetailPage extends ProductDetailPageBase {

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
