package solvd.carina.demo.gui.ios.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.components.NativeSelectComponentBase;
import solvd.carina.demo.gui.common.models.SortOption;

import java.util.List;

public class iOSNativeSelectComponent extends NativeSelectComponentBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther")
    private List<ExtendedWebElement> options;

    public iOSNativeSelectComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void selectOption(SortOption sortOption) {
        options.stream()
                .filter(opt -> opt.getAttribute("label") != null &&
                        opt.getAttribute("label").equalsIgnoreCase(sortOption.getName()))
                .findFirst().
                ifPresent(ExtendedWebElement::click);
    }
}
