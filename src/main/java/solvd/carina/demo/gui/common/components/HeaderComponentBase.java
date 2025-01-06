package solvd.carina.demo.gui.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.LeftSidebarPageBase;
import solvd.carina.demo.gui.ios.pages.CartPage;

public abstract class HeaderComponentBase extends AbstractUIObject {
    public HeaderComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract CartPage clickCartButton();

    public abstract Integer getCartCount();

    public abstract LeftSidebarPageBase clickMenuButton();
}
