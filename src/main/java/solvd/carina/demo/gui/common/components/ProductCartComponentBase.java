package solvd.carina.demo.gui.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class ProductCartComponentBase extends AbstractUIObject {

    public ProductCartComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract Integer getAmount();

    public abstract String getTitle();

    public abstract Float getPrice();
}
