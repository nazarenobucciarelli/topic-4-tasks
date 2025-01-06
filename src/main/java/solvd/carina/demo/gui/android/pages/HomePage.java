package solvd.carina.demo.gui.android.pages;


import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.components.HeaderComponent;
import solvd.carina.demo.gui.android.components.ProductListComponent;
import solvd.carina.demo.gui.common.components.HeaderComponentBase;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.HomePageBase;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/.")
    private HeaderComponent header;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]")
    private ExtendedWebElement sortItemsButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Item\"]")
    private List<ProductListComponent> products;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectSortOption(SortOption sortOption) {
        sortItemsButton.click();
    }

    @Override
    public List<ProductListComponent> getProducts() {
        return products;
    }

    @Override
    public HeaderComponentBase getHeader() {
        return header;
    }
}
