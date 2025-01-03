package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.ios.components.HeaderComponent;
import solvd.carina.demo.gui.ios.components.ProductListComponent;

import java.util.List;

public class HomePage extends AbstractPage {

    @ExtendedFindBy(iosPredicate = "name == \"PRODUCTS\" AND label == \"PRODUCTS\" AND value == \"PRODUCTS\"")
    private ExtendedWebElement productsTitle;

    @ExtendedFindBy(iosPredicate = "name == \"test-Item\"")
    private List<ProductListComponent> products;

    @FindBy(xpath = "//XCUIElementTypeOther[@name=\"headerContainer\"]/parent::XCUIElementTypeOther")
    private HeaderComponent header;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(productsTitle);
    }

    public List<ProductListComponent> getProducts() {
        return products;
    }

    public HeaderComponent getHeader() {
        return header;
    }
}
