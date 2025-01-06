package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.pages.ProductDetailPageBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView[1]")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView[2]")
    private ExtendedWebElement description;

    @ExtendedFindBy(accessibilityId = "test-Price")
    private ExtendedWebElement price;

    @ExtendedFindBy(accessibilityId = "test-ADD TO CART")
    private ExtendedWebElement addToCartButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean areElementsPresent() {
        return title.isPresent() && description.isPresent() && price.isPresent() && addToCartButton.isPresent();
    }
}
