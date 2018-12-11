import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class CreateUserPage extends AbsPageObject {

    List<WebElement> elements;

    public int textCounter = 0;
    public int passCounter = 0;

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

    public CreateUserPage setUsername (String username){
        usernameInput.sendKeys(username);
        return this;
    }

    public CreateUserPage setFirstPassword (String password1){
        passwordFirstInput.sendKeys(password1);
        return this;
    }
    public CreateUserPage setSecondPassword (String password2){
        passwordSecondInput.sendKeys(password2);
        return this;
    }

    public CreateUserPage setFulname (String fullname){
        fullnameInput.sendKeys(fullname);
        return this;
    }

    public CreateUserPage setEmail (String email){
        emailInput.sendKeys(email);
        return this;
    }

    public void initiateNewUserCreation(String username,String password1,String password2,String fullname, String email){
        setUsername(username);
        setFirstPassword(password1);
        setSecondPassword(password2);
        setFulname(fullname);
        setEmail(email);
        createButton.click();
    }



}
