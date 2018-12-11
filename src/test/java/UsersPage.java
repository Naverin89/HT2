import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;


public class UsersPage extends AbsPageObject {

    @FindBy(linkText = "Create User")
    private WebElement linkCreateUser;


    public UsersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCreateUserLinkExist() {
        if (this.linkCreateUser.isDisplayed()) {
            return true;
        }
        return false;
    }

    public UsersPage goCreateUser() {
        linkCreateUser.click();
        return this;
    }

}
