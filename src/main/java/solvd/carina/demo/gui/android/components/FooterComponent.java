package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends AbstractUIObject {

    @FindBy(xpath = "//android.widget.TextView[@text=\"© 2025 Sauce Labs. All Rights Reserved.\"]")
    private ExtendedWebElement allRightsReservedLabel;

    public FooterComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getAllRightsReservedLabel() {
        return allRightsReservedLabel;
    }
}
