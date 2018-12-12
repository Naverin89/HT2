import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class CreateUserPage extends AbsPageObject {

    //Collection of web elements on the form with inputs
    List<WebElement> elements;

    public int textCounter = 0;
    public int passCounter = 0;
    private String user = "someuser";
    private String password = "somepassword";
    private String fullName = "Some Full Name";
    private String email = "some@addr.dom";

    @FindBy(xpath = "//div[@id='main-panel']/form")
    private WebElement createUserForm;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name = 'password1']")
    private WebElement passwordFirstInput;

    @FindBy(xpath = "//input[@name = 'password2']")
    private WebElement passwordSecondInput;

    @FindBy(xpath = "//input[@name = 'fullname']")
    private WebElement fullnameInput;

    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement emailInput;

    @FindBy(id = "yui-gen1")
    private WebElement createButton;

    public CreateUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Checking if registration form has appropriate amounts of text and passwords fields
    public boolean isCorrectAmountAndTypeOfFields(WebDriver driver) {

        elements = driver.findElements(By.xpath("//form/div/table//input"));
        for (WebElement element : elements) {
            if (element.getAttribute("type").equals("text") && element.getAttribute("value").isEmpty()) {
                textCounter++;
            } else if (element.getAttribute("type").equals("password") && element.getAttribute("value").isEmpty()) {
                passCounter++;
            }
        }
        if (textCounter == 3 && passCounter == 2) {
            return true;
        }
        return false;
    }

    //Initiating all fields with required texts
    public CreateUserPage setUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public CreateUserPage setFirstPassword(String password1) {
        passwordFirstInput.sendKeys(password1);
        return this;
    }

    public CreateUserPage setSecondPassword(String password2) {
        passwordSecondInput.sendKeys(password2);
        return this;
    }

    public CreateUserPage setFulname(String fullname) {
        fullnameInput.sendKeys(fullname);
        return this;
    }

    public CreateUserPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    //Filling all of the fields on the form and click on the button
    public void initiateNewUserCreation() {
        setUsername(user);
        setFirstPassword(password);
        setSecondPassword(password);
        setFulname(fullName);
        setEmail(email);
        createButton.click();
    }

    public String getButton() {
        return createButton.getCssValue("background-color");
    }

}
