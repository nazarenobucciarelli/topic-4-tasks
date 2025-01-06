package solvd.carina.demo.gui.ios.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.ios.pages.ProductDetailPage;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductListComponentBase.class)
public class ProductListComponent extends ProductListComponentBase {

    @ExtendedFindBy(iosPredicate = "name == \"test-Item title\"")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"ADD TO CART\"`]")
    private ExtendedWebElement addToCartButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"REMOVE\"`]")
    private ExtendedWebElement removeButton;


    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name == \"test-Price\"`]")
    private ExtendedWebElement price;

    public ProductListComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitle() {
        return title.getText();
    }

    public Float getPrice() {
        return Float.parseFloat(price.getText().substring(1));
    }

    public Product clickAddToCartButton() {
        addToCartButton.click();
        return new Product(getTitle(), getPrice());
    }

    public ProductDetailPage goToDetail(){
        title.click();
        return new ProductDetailPage(getDriver());
    }

    public boolean isRemoveButtonPresent() {
        return removeButton.isPresent();
    }

    public void clickRemoveButton() {
        removeButton.click();
    }
}
