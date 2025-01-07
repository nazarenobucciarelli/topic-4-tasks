package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.components.NativeSelectComponentBase;
import solvd.carina.demo.gui.common.models.SortOption;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NativeSelectComponentBase.class)
public class AndroidNativeSelectComponent extends NativeSelectComponentBase {

    @FindBy(xpath = "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    List<ExtendedWebElement> options;

    public AndroidNativeSelectComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void selectOption(SortOption sortOption) {
        options.stream()
                .filter(opt -> opt.getAttribute("text") != null &&
                        opt.getAttribute("text").equalsIgnoreCase(sortOption.getName()))
                .findFirst().//fixxxx
                ifPresent(ExtendedWebElement::click);
    }
}
