package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductDetailPageBase extends AbstractPage {
    public ProductDetailPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean areElementsPresent();
}
