package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends AbsPageObject {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //click link to go "Manage Jenkins" page
    public DashboardPage goToManageJenk() {
        manageJenkins.click();
        return this;
    }

}
