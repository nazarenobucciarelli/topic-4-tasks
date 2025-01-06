package solvd.carina.demo.gui.ios.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.HomePageBase;
import solvd.carina.demo.gui.ios.components.HeaderComponent;
import solvd.carina.demo.gui.ios.components.ProductListComponent;
import solvd.carina.demo.gui.ios.components.iOSNativeSelectComponent;

import java.util.List;

public class HomePage extends HomePageBase {

    @ExtendedFindBy(iosPredicate = "name == \"PRODUCTS\" AND label == \"PRODUCTS\" AND value == \"PRODUCTS\"")
    private ExtendedWebElement productsTitle;

    @ExtendedFindBy(iosPredicate = "name == \"test-Item\"")
    private List<ProductListComponentBase> products;

    @FindBy(xpath = "//XCUIElementTypeOther[@name=\"headerContainer\"]/parent::XCUIElementTypeOther")
    private HeaderComponent header;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Modal Selector Button\"`]" +
            "/XCUIElementTypeOther/XCUIElementTypeOther")
    private ExtendedWebElement sortItemsButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"Selector container Cancel\"`][3]")
    private iOSNativeSelectComponent nativeSelect;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(productsTitle);
    }

    public void selectSortOption(SortOption sortOption) {
        sortItemsButton.click();
        nativeSelect.selectOption(sortOption);
    }

    public List<ProductListComponentBase> getProducts() {
        return products;
    }

    public HeaderComponent getHeader() {
        return header;
    }
}
