package solvd.carina.demo.gui.ios.components;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import solvd.carina.demo.gui.common.components.ProductCartComponentBase;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductCartComponentBase.class)
public class ProductCartComponent extends ProductCartComponentBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Amount\"`]")
    private ExtendedWebElement amount;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Description\"`]/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == \"test-Price\"`]/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement price;

    public ProductCartComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Integer getAmount() {
        return Integer.parseInt(amount.getText());
    }

    public String getTitle() {
        return title.getText();
    }

    public Float getPrice() {
        return Float.parseFloat(price.getText().substring(1));
    }
}
