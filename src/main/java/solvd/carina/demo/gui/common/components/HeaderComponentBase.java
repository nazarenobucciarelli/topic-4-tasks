package solvd.carina.demo.gui.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.pages.CartPageBase;
import solvd.carina.demo.gui.common.pages.LeftSidebarPageBase;

public abstract class HeaderComponentBase extends AbstractUIObject {
    public HeaderComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract CartPageBase clickCartButton();

    public abstract Integer getCartCount();

    public abstract LeftSidebarPageBase clickMenuButton();
}
