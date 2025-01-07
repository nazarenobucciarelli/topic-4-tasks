package solvd.carina.demo.gui.common.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.models.SortOption;

public abstract class NativeSelectComponentBase extends AbstractUIObject {
    public NativeSelectComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void selectOption(SortOption sortOption);
}
