package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.components.FooterComponent;
import solvd.carina.demo.gui.android.components.ProductCartComponent;
import solvd.carina.demo.gui.common.models.ProductCart;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.common.pages.CheckoutPageBase;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

    @ExtendedFindBy(accessibilityId = "test-CHECKOUT")
    private ExtendedWebElement checkoutButton;

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Cart Content\"]")
    private ExtendedWebElement container;

    @FindBy(xpath = "//android.widget.TextView[@text=\"© 2025 Sauce Labs. All Rights Reserved.\"]/..")
    private FooterComponent footer;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCart> getProducts() {
        int maxIterations = 6;
        List<ProductCart> products = new LinkedList<>();
        do {
            try {
                List<WebElement> elements = driver.findElements(By
                        .xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]"));

                elements = elements.stream().filter(element -> {
                    try {
                        WebElement upperBoundElement = element.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Amount\"]"));
                        WebElement lowerBoundElement = element.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]"));
                        return upperBoundElement.isDisplayed() && lowerBoundElement.isDisplayed();
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                }).collect(Collectors.toList());

                List<ProductCartComponent> productCartItems = elements.stream()
                        .map(element -> new ProductCartComponent(driver, element))
                        .collect(Collectors.toList());

                for (ProductCartComponent productCartItem : productCartItems) {
                    try {
                        String productTitle = productCartItem.getTitle();
                        if (isNewProduct(products, productTitle)) {
                            products.add(new ProductCart(productTitle, productCartItem.getPrice(), productCartItem.getAmount()));
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Product elements are not visible yet");
                    }
                }
            } catch (StaleElementReferenceException e) {
                throw new RuntimeException(e);
            }
        } while (!swipe(footer.getAllRightsReservedLabel(), Direction.UP, 1, 600) && --maxIterations != 0);
        products.forEach(productCart -> {
            System.out.println(productCart.getTitle());
        });
        return products;
    }

    private boolean isNewProduct(List<ProductCart> products, String productTitle) {
        return products.stream().noneMatch(product -> product.getTitle().equals(productTitle));
    }

    @Override
    public CheckoutPageBase clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
