package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class LeftSidebarPageBase extends AbstractPage {
    public LeftSidebarPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SignInPageBase clickLogoutButton();
}
