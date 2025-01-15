package solvd.carina.demo.gui.android.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductCartComponentBase.class)
public class ProductCartComponent extends ProductCartComponentBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Amount\"]//android.widget.TextView")
    private ExtendedWebElement amount;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView")
    private ExtendedWebElement price;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private ExtendedWebElement title;

    public ProductCartComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public Integer getAmount() {
        return Integer.parseInt(amount.getText());
    }

    @Override
    public String getTitle() {
        return title.getText();
    }

    @Override
    public Float getPrice() {
        return Float.parseFloat(price.getText().substring(1));
    }
}
