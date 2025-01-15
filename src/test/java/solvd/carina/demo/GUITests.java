package solvd.carina.demo;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.commons.math3.util.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.models.ProductCart;
import solvd.carina.demo.gui.common.models.SortOption;
import solvd.carina.demo.gui.common.pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Test(enabled = true)
    public void verifyCartContentTest() {
        Pair<HomePageBase, List<Product>> addAllProductsToCartResult = addAllProductsToCart();
        HomePageBase homePage = addAllProductsToCartResult.getFirst();
        List<Product> products = addAllProductsToCartResult.getSecond();
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        List<ProductCart> cartPageProducts = cartPage.getProducts();
        System.out.println(cartPageProducts);
        products.forEach(product -> {
            Assert.assertTrue(cartPageProducts.stream()
                    .anyMatch(productCart -> Objects.equals(productCart.getTitle(), product.getTitle())),
                    "Cart should contain product " + product.getTitle());
        });
    }

    @Test(enabled = false)
    public void verifyProductDetailTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        ProductDetailPageBase productDetailPage = product.goToDetail();
        Assert.assertTrue(productDetailPage.areElementsPresent(), "Elements should be present");
    }

    @Test(enabled = false)
    public void verifyAddingProductToCartTest() {
        Pair<ProductListComponentBase, HomePageBase> addProductToCartResult = addRandomProductToCart();
        ProductListComponentBase product = addProductToCartResult.getFirst();
        HomePageBase homePage = addProductToCartResult.getSecond();
        Assert.assertTrue(product.isRemoveButtonPresent(), "Remove button " +
                "should be present");
        Assert.assertEquals(homePage.getHeader().getCartCount(), 1, "Cart " +
                "count should be 1");
    }

    @Test(enabled = false)
    public void verifyRemovingProductFromCartTest() {
        Pair<ProductListComponentBase, HomePageBase> addProductToCartResult = addRandomProductToCart();
        ProductListComponentBase product = addProductToCartResult.getFirst();
        HomePageBase homePage = addProductToCartResult.getSecond();
        Assert.assertTrue(product.isRemoveButtonPresent(), "Remove button should be present");
        product.clickRemoveButton();
        Assert.assertNull(homePage.getHeader().getCartCount(), "Cart count should be null");
    }

    @Test(enabled = false)
    public void checkoutFlowTest() {
        Pair<ProductListComponentBase, HomePageBase> addProductToCartResult = addRandomProductToCart();
        HomePageBase homePage = addProductToCartResult.getSecond();
        CartPageBase cartPage = homePage.getHeader().clickCartButton();
        CheckoutPageBase checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.checkout("John", "Doe", "12345");
        Assert.assertTrue(checkoutPage.isThanksForYourOrderTextDisplayed(), "Thanks for your order text " +
                "should be displayed");
    }

    @Test(enabled = false)
    public void sortProductsByPriceTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be opened");
        HomePageBase homePage = (HomePageBase) page;
        homePage.selectSortOption(SortOption.PRICE_LOW_TO_HIGH);
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        Assert.assertTrue(areProductsSortedByPrice(products), "Products should be sorted from low to high price");
    }

    @Test(enabled = false)
    public void verifyLogOutTest() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be opened");
        HomePageBase homePage = (HomePageBase) page;
        LeftSidebarPageBase leftSidebar = homePage.getHeader().clickMenuButton();
        SignInPageBase signInPage = leftSidebar.clickLogoutButton();
        Assert.assertTrue(signInPage.isPageOpened(), "Login page should be opened");
    }

    @Test(enabled = false)
    public void verifyErrorEmptyFieldsLoginTest() {
        AbstractPage page = login("", "");
        Assert.assertTrue(page instanceof SignInPageBase && page.isPageOpened(), "Sign in page should be opened");
        SignInPageBase signInPage = (SignInPageBase) page;
        Assert.assertTrue(signInPage.isRequiredUsernameErrorDisplayed(), "Required username text should be displayed");
    }

    // Helper methods

    private AbstractPage login(String username, String password) {
        SignInPageBase signInPage = initPage(SignInPageBase.class);
        signInPage.typeUsername(username);
        signInPage.typePassword(password);
        return signInPage.clickLoginButton();
    }

    private boolean areProductsSortedByPrice(List<? extends ProductListComponentBase> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).getPrice() > products.get(i + 1).getPrice()) {
                return false;
            }
        }
        return true;
    }

    private Pair<ProductListComponentBase, HomePageBase> addRandomProductToCart() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<? extends ProductListComponentBase> products = homePage.getProducts();
        ProductListComponentBase product = products.get(new Random().nextInt(products.size() - 1));
        product.clickAddToCartButton();
        return new Pair<>(product, homePage);
    }

    private Pair<HomePageBase, List<Product>> addAllProductsToCart() {
        AbstractPage page = login("standard_user", "secret_sauce");
        Assert.assertTrue(page instanceof HomePageBase && page.isPageOpened(), "Home page should be" +
                " opened");
        HomePageBase homePage = (HomePageBase) page;
        List<Product> productsList = new ArrayList<>();
        homePage.getProducts().forEach(product -> {
            Product productList = product.clickAddToCartButton();
            productsList.add(productList);
        });
        return new Pair<>(homePage, productsList);
    }

}
