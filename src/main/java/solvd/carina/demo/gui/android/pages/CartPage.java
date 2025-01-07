package solvd.carina.demo.gui.android.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.android.components.ProductCartComponent;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.common.pages.CheckoutPageBase;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

    @ExtendedFindBy(accessibilityId = "test-CHECKOUT")
    private ExtendedWebElement checkoutButton;

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Cart Content\"]")
    private ExtendedWebElement container;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<? extends ProductCartComponentBase> getProducts() {
        List<ProductCartComponent> productCartComponents = new ArrayList<>();
        List<Product> foundCartProducts = new ArrayList<>();
        boolean canSwipeUp = true;
        while (canSwipeUp) {
            List<WebElement> cartProducts = driver.findElements(By.xpath(
                    "//android.view.ViewGroup[@content-desc=\"test-Item\"]"));
            for (WebElement cartProduct : cartProducts) {
                ProductCartComponent productCartComponent = new ProductCartComponent(driver, cartProduct);
                try {
                    WebElement productTitle = cartProduct.findElement(By.xpath(
                            "//android.view.ViewGroup[@content-desc=\"test-Description\"]" +
                                    "//android.widget.TextView[1]"));
                    WebElement productDescription = cartProduct.findElement(By.xpath(
                            "//android.view.ViewGroup[@content-desc=\"test-Description\"]//" +
                                    "android.widget.TextView[2]"));
                    if (productTitle.isDisplayed() && productDescription.isDisplayed()) {
                        Product product = new Product(productCartComponent.getTitle(), productCartComponent.getPrice());
                        if (!foundCartProducts.contains(product)) {
                            foundCartProducts.add(product);
                            productCartComponents.add(productCartComponent);
                        }
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Product not totally found yet");
                }

            }
            try {
                if (driver.findElement(By.xpath("//android.widget.TextView[@text=\"Terms of Service" +
                        " | Privacy Policy\"]")).isDisplayed()) {
                    canSwipeUp = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Footer not found yet");
            }
            swipeInContainer(container, Direction.UP, 1000);
        }
        boolean canSwipeDown = true;
        while (canSwipeDown) {
            swipeInContainer(container, Direction.DOWN, 3000);
            try {
                WebElement productTitle = driver.findElement(
                        By.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]//" +
                                "android.widget.TextView[@text=\"" + foundCartProducts.get(0).getTitle() + "\"]"));
                if (productTitle.isDisplayed()) {
                    canSwipeDown = false;
                    System.out.println("First element found!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Not in the first item yet");
            }
        }
        return productCartComponents;
    }

    @Override
    public CheckoutPageBase clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
