package solvd.carina.demo;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.ios.components.ProductCartComponent;
import solvd.carina.demo.gui.ios.components.ProductListComponent;
import solvd.carina.demo.gui.ios.pages.CartPage;
import solvd.carina.demo.gui.ios.pages.HomePage;
import solvd.carina.demo.gui.ios.pages.ProductDetailPage;
import solvd.carina.demo.gui.ios.pages.SignInPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GUITests extends AbstractTest {

    @Test(enabled = false)
    public void successfulLoginTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePage && page.isPageOpened());
    }

    @Test(enabled = false)
    public void failedLoginInvalidCredentialsTest() {
        AbstractPage page = login("invalid_user", "invalid_password");
        Assert.assertTrue(page instanceof SignInPage && page.isPageOpened());
        SignInPage signInPage = (SignInPage) page;
        Assert.assertTrue(signInPage.isInvalidCredentialsErrorMessageDisplayed());
    }

    @Test(enabled = false)
    public void verifyCartContentTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePage && page.isPageOpened());
        HomePage homePage = (HomePage) page;
        List<Product> productsList = new ArrayList<>();
        homePage.getProducts().forEach(product -> {
            Product productList = product.clickAddToCartButton();
            productsList.add(productList);
        });
        CartPage cartPage = homePage.getHeader().clickCartButton();
        cartPage.getProducts().forEach(product -> {
            Assert.assertTrue(productsList.contains(new Product(product.getTitle(), product.getPrice())));
            Assert.assertEquals((int) product.getAmount(), 1);
        });
    }

    @Test(enabled = false)
    public void verifyProductDetailTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePage && page.isPageOpened());
        HomePage homePage = (HomePage) page;
        List<ProductListComponent> products = homePage.getProducts();
        ProductListComponent product = products.get(new Random().nextInt(products.size() - 1));
        ProductDetailPage productDetailPage = product.goToDetail();
        Assert.assertTrue(productDetailPage.areElementsPresent());
    }

    @Test(enabled = false)
    public void verifyAddingProductToCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePage && page.isPageOpened());
        HomePage homePage = (HomePage) page;
        List<ProductListComponent> products = homePage.getProducts();
        ProductListComponent product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent());
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1);
    }

    @Test(enabled = true)
    public void verifyRemovingProductFromCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePage && page.isPageOpened());
        HomePage homePage = (HomePage) page;
        List<ProductListComponent> products = homePage.getProducts();
        ProductListComponent product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent());
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1);
    }

    // Helper methods

    public AbstractPage login(String username, String password) {
        SignInPage signInPage = new SignInPage(getDriver());
        signInPage.typeUsername(username);
        signInPage.typePassword(password);
        return signInPage.clickLoginButton();
    }
}
