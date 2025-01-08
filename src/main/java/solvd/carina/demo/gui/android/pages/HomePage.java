package solvd.carina.demo.gui.android.pages;


import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.components.AndroidNativeSelectComponent;
import solvd.carina.demo.gui.android.components.HeaderComponent;
import solvd.carina.demo.gui.android.components.ProductListComponent;
import solvd.carina.demo.gui.common.components.HeaderComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.HomePageBase;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/..")
    private HeaderComponent header;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]")
    private ExtendedWebElement sortItemsButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    private ExtendedWebElement productsTitle;

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]")
    private ExtendedWebElement container;

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"Selector container\"]")
    private AndroidNativeSelectComponent select;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(productsTitle);
    }

    @Override
    public void selectSortOption(SortOption sortOption) {
        sortItemsButton.click();
        select.selectOption(sortOption);
    }

    @Override
    public List<ProductListComponent> getProducts() {
        boolean canScrollMore = true;
        List<ProductListComponent> productListComponents = new ArrayList<>();
        List<Product> foundProducts = new ArrayList<>();
        while (canScrollMore) {
            swipeInContainer(container, Direction.UP, 1000);
            List<WebElement> products = driver
                    .findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]"));
            for (WebElement product : products) {
                ProductListComponent productListComponent = new ProductListComponent(driver, product);
                try{
                    swipe(productListComponent.getAddButton());
                    Product prod = new Product(productListComponent.saveTitleElement(),productListComponent.savePriceElement());
                    if (!foundProducts.contains(prod)) {
                        foundProducts.add(prod);
                        productListComponents.add(productListComponent);
                    }
                } catch (Exception e){
                    System.out.println("Product not totally found yet");
                }
            }
            try {
                if (driver.findElement(By.xpath("//android.widget.TextView[@text=\"Terms of Service" +
                        " | Privacy Policy\"]")).isDisplayed()) {
                    canScrollMore = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("No footer found yet");
            }
        }
        System.out.println("Found " + foundProducts.size() + " products");
        boolean canSwipeDown = true;
        while (canSwipeDown) {
            swipeInContainer(container, Direction.DOWN, 1000);
            try {
                if (driver
                        .findElement(By.xpath("//android.widget.TextView[@content-desc=\"test-Item title\"" +
                                " and @text=\"" + foundProducts.get(0).getTitle() + "\"]")).isDisplayed()) {
                    canSwipeDown = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Not in the first item yet");
            }
        }
        return productListComponents;
    }

    @Override
    public HeaderComponentBase getHeader() {
        return header;
    }
}
