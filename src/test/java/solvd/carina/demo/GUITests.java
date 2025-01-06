package solvd.carina.demo;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.*;
import solvd.carina.demo.gui.ios.pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUITests extends AbstractTest {

    @Test(enabled = true)
    public void successfulLoginTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
    }

    @Test(enabled = true)
    public void failedLoginInvalidCredentialsTest() {
        AbstractPage page = login("invalid_user", "invalid_password");
        Assert.assertTrue(page instanceof SignInPageBase && page.isPageOpened());
        SignInPageBase signInPage = (SignInPageBase) page;
        Assert.assertTrue(signInPage.isInvalidCredentialsErrorMessageDisplayed());
    }

    @Test(enabled = true)
    public void verifyCartContentTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        List<Product> productsList = new ArrayList<>();
        homePage.getProducts().forEach(product -> {
            Product productList = product.clickAddToCartButton();
            productsList.add(productList);
        });
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        cartPage.getProducts().forEach(product -> {
            Assert.assertTrue(productsList.contains(new Product(product.getTitle(), product.getPrice())));
            Assert.assertEquals((int) product.getAmount(), 1);
        });
    }

    @Test(enabled = true)
    public void verifyProductDetailTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        List<ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        ProductDetailPageBase productDetailPage = product.goToDetail();
        Assert.assertTrue(productDetailPage.areElementsPresent());
    }

    @Test(enabled = true)
    public void verifyAddingProductToCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        List<ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent());
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1);
    }

    @Test(enabled = true)
    public void verifyRemovingProductFromCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        List<ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent());
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1);
    }

    @Test(enabled = true)
    public void checkoutFlowTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        List<ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        CheckoutPageBase checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterZip("12345");
        checkoutPage.clickContinueButton();
        checkoutPage.clickFinishButton();
        Assert.assertTrue(checkoutPage.isThanksForYourOrderTextDisplayed());
    }

    @Test(enabled = true)
    public void sortProductsByPriceTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        homePage.selectSortOption(SortOption.PRICE_LOW_TO_HIGH);
        List<ProductListComponentBase> products = homePage.getProducts();
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(products.get(i).getPrice() <= products.get(i + 1).getPrice());
        }
    }

    @Test(enabled = true)
    public void verifyLogOutTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened());
        HomePageBase homePage = (HomePageBase) page;
        LeftSidebarPageBase leftSidebar = homePage.getHeader().clickMenuButton();
        SignInPageBase signInPage = leftSidebar.clickLogoutButton();
        Assert.assertTrue(signInPage.isPageOpened());
    }

    @Test(enabled = true)
    public void verifyErrorEmptyFieldsLoginTest() {
        AbstractPage page = login("", "");
        Assert.assertTrue(page instanceof SignInPageBase && page.isPageOpened());
        SignInPageBase signInPage = (SignInPageBase) page;
        Assert.assertTrue(signInPage.isRequiredUsernameErrorDisplayed());
    }

    // Helper methods

    public AbstractPage login(String username, String password) {
        SignInPageBase signInPage = new SignInPage(getDriver());
        signInPage.typeUsername(username);
        signInPage.typePassword(password);
        return signInPage.clickLoginButton();
    }
}
