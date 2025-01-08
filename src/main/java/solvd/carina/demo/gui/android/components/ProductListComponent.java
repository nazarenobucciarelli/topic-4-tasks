package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
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
public class ProductListComponent extends ProductListComponentBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\"]")
    private ExtendedWebElement titleElement;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\"]")
    private ExtendedWebElement priceElement;

    @ExtendedFindBy(accessibilityId = "test-ADD TO CART")
    private ExtendedWebElement addButton;

    @ExtendedFindBy(accessibilityId = "test-REMOVE")
    private ExtendedWebElement removeButton;

    private String title;

    private Float price;

    public ProductListComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String saveTitleElement() {
        String title = titleElement.getText();
        this.title = title;
        return title;
    }

    public Float savePriceElement() {
        Float price = Float.parseFloat(priceElement.getText().substring(1));
        this.price = price;
        return price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Float getPrice() {
        return this.price;
    }

    @Override
    public Product clickAddToCartButton() {
        swipe(addButton);
        addButton.click();
        return new Product(getTitle(), getPrice());
    }

    @Override
    public ProductDetailPageBase goToDetail() {
        titleElement.click();
        return new ProductDetailPage(driver);
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return removeButton.isPresent();
    }

    public ExtendedWebElement getAddButton() {
        return addButton;
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }

}
