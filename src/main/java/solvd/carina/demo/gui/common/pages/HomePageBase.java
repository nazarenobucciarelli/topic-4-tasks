package solvd.carina.demo.gui.common.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.components.HeaderComponentBase;
import solvd.carina.demo.gui.common.components.ProductListComponentBase;
import solvd.carina.demo.gui.common.models.SortOption;

import java.util.List;

public abstract class HomePageBase extends AbstractPage {
    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void selectSortOption(SortOption sortOption);

    public abstract List<? extends ProductListComponentBase> getProducts();

    public abstract HeaderComponentBase getHeader();
}
