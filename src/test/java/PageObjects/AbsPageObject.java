package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// Accesor for all of pages in a test
public abstract class AbsPageObject {

    protected WebDriver driver;
    private WebDriverWait wait;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

}


