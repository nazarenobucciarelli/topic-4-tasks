package solvd.carina.demo.gui.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.models.Product;
import solvd.carina.demo.gui.common.pages.ProductDetailPageBase;

public abstract class ProductListComponentBase extends AbstractUIObject {
    public ProductListComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract String getTitle();

    public abstract Float getPrice();

    public abstract Product clickAddToCartButton();

    public abstract ProductDetailPageBase goToDetail();

    public abstract boolean isRemoveButtonPresent();

    public abstract void clickRemoveButton();
}
