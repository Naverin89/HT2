import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbsPageObject {

    private WebDriver driver;
    private WebDriverWait wait;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

}


