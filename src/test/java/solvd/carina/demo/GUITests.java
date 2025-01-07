package solvd.carina.demo;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GUITests extends AbstractTest {

    @Test(enabled = false)
    public void successfulLoginTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page is not opened");
    }

    @Test(enabled = false)
    public void failedLoginInvalidCredentialsTest() {
        AbstractPage page = login("invalid_user", "invalid_password");
        Assert.assertTrue(page instanceof SignInPageBase && page.isPageOpened(), "Sign In page should" +
                " be opened");
        SignInPageBase signInPage = (SignInPageBase) page;
        Assert.assertTrue(signInPage.isInvalidCredentialsErrorMessageDisplayed(), "Invalid credentials text " +
                "should be displayed");
    }

    @Test(enabled = false)
    public void verifyCartContentTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<Product> productsList = new ArrayList<>();
        homePage.getProducts().forEach(product -> {
            Product productList = product.clickAddToCartButton();
            productsList.add(productList);
        });
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        List<? extends ProductCartComponentBase> cartPageProducts = cartPage.getProducts();
        List<Product> cartProducts = cartPageProducts.stream().map(prod ->
        {
            Assert.assertEquals(prod.getAmount(),1,"Amount should be 1");
            return new Product(prod.getTitle(), prod.getPrice());
        }).collect(Collectors.toList());
        productsList.forEach(product -> {
            Assert.assertTrue(cartProducts.contains(product), "Cart should contain product " + product.getTitle());
        });
    }

    @Test(enabled = false)
    public void verifyProductDetailTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(),"Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        ProductDetailPageBase productDetailPage = product.goToDetail();
        Assert.assertTrue(productDetailPage.areElementsPresent(), "Elements should be present");
    }

    @Test(enabled = false)
    public void verifyAddingProductToCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent(), "Remove button should be present");
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1, "Cart count should be 1");
    }

    @Test(enabled = false)
    public void verifyRemovingProductFromCartTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        Assert.assertTrue(product.isRemoveButtonPresent(), "Remove button should be present");
        product.clickRemoveButton();
        Assert.assertNull(homePage.getHeader().getCartCount(), "Cart count should be null");
    }

    @Test(enabled = false)
    public void checkoutFlowTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(),"Home page should be " +
                "opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        CheckoutPageBase checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterZip("12345");
        checkoutPage.clickContinueButton();
        checkoutPage.clickFinishButton();
        Assert.assertTrue(checkoutPage.isThanksForYourOrderTextDisplayed(),"Thanks for your order text " +
                "should be displayed");
    }

    @Test(enabled = true)
    public void sortProductsByPriceTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be opened");
        HomePageBase homePage = (HomePageBase) page;
        homePage.selectSortOption(SortOption.PRICE_LOW_TO_HIGH);
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(products.get(i).getPrice() <= products.get(i + 1).getPrice(), "Products" +
                    " should be sorted from low to high price");
        }
    }

    @Test(enabled = false)
    public void verifyLogOutTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be opened");
        HomePageBase homePage = (HomePageBase) page;
        LeftSidebarPageBase leftSidebar = homePage.getHeader().clickMenuButton();
        SignInPageBase signInPage = leftSidebar.clickLogoutButton();
        Assert.assertTrue(signInPage.isPageOpened(),"Login page should be opened");
    }

    @Test(enabled = false)
    public void verifyErrorEmptyFieldsLoginTest() {
        AbstractPage page = login("", "");
        Assert.assertTrue(page instanceof SignInPageBase && page.isPageOpened(),"Sign in page should be opened");
        SignInPageBase signInPage = (SignInPageBase) page;
        Assert.assertTrue(signInPage.isRequiredUsernameErrorDisplayed(), "Required username text should be displayed");
    }

    // Helper methods

    public AbstractPage login(String username, String password) {
        SignInPageBase signInPage = initPage(SignInPageBase.class);
        signInPage.typeUsername(username);
        signInPage.typePassword(password);
        return signInPage.clickLoginButton();
    }
}
