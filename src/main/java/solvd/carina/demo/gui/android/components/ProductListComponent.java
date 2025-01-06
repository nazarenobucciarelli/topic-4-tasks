package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.pages.ProductDetailPage;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.pages.ProductDetailPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductListComponentBase.class)
public class ProductListComponent extends ProductListComponentBase {

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\"]")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\"]")
    private ExtendedWebElement price;

    @ExtendedFindBy(accessibilityId = "test-ADD TO CART")
    private ExtendedWebElement addButton;

    @ExtendedFindBy(accessibilityId = "test-REMOVE")
    private ExtendedWebElement removeButton;

    public ProductListComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public Float getPrice() {
        return Float.parseFloat(price.getText().substring(1));
    }

    @Override
    public Product clickAddToCartButton() {
        addButton.click();
        return new Product(getTitle(), getPrice());
    }

    @Override
    public ProductDetailPageBase goToDetail() {
        title.click();
        return new ProductDetailPage(driver);
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return removeButton.isPresent();
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }
}
