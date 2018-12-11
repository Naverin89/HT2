import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ManageJenkinsPage extends AbsPageObject {

    private String MANAGE_JENKINS_URL = "http://localhost:8080/manage";

    @FindBy(xpath = "//a[@title = 'Manage Users']")
    private WebElement manageUserLinkElem;

    @FindBy(xpath = "//a[@title = 'Manage Users']/dl/dt")
    private WebElement dtElem;

    @FindBy(xpath = "//a[@title = 'Manage Users']/dl/dd")
    private WebElement ddElem;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ManageJenkinsPage goToManageUsers() {
        manageUserLinkElem.click();
        return this;
    }

    public boolean isDtExist() {
        if (this.dtElem.isDisplayed()) {
            if (this.dtElem.getText().equals("Manage Users")) {
                return true;
            }
        }
        return false;
    }

    public boolean isDdExist() {
        if (this.ddElem.isDisplayed()) {
            if (this.ddElem.getText().equals("Create/delete/modify users that can log in to this Jenkins")) {
                return true;
            }
        }
        return false;
    }

    public String getMANAGE_JENKINS_URL() {
        return MANAGE_JENKINS_URL;
    }

}
